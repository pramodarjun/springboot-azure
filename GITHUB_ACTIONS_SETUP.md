# GitHub Actions Setup for Azure Deployment

## Step 1: Create Azure Service Principal with Federated Credentials

Run this command in Azure CLI:

```bash
az ad sp create-for-rbac --name "spring-boot-demo-github" --role contributor \
    --scopes /subscriptions/{subscription-id}/resourceGroups/{resource-group-name} \
    --sdk-auth
```

This will output JSON with credentials. Save this output.

## Step 2: Configure Federated Identity Credential

```bash
az ad app federated-credential create --id <APPLICATION_ID> --parameters '{
    "name": "github-federated-credential",
    "issuer": "https://token.actions.githubusercontent.com",
    "subject": "repo:{your-github-username}/{your-repo-name}:ref:refs/heads/main",
    "audiences": ["api://AzureADTokenExchange"]
}'
```

## Step 3: Add GitHub Secrets

Go to your GitHub repository → Settings → Secrets and variables → Actions → New repository secret

Add these three secrets:

1. **AZURE_CLIENT_ID** - Application (client) ID from service principal
2. **AZURE_TENANT_ID** - Directory (tenant) ID from service principal  
3. **AZURE_SUBSCRIPTION_ID** - Your Azure subscription ID

## Step 4: Get the Values

```bash
# Get subscription ID
az account show --query id -o tsv

# Get tenant ID
az account show --query tenantId -o tsv

# Get client ID (from service principal creation output)
# Look for "clientId" in the JSON output from Step 1
```

## Alternative: Using Publish Profile (Simpler)

If federated credentials are complex, use publish profile instead:

1. Go to Azure Portal → Your App Service → Download publish profile
2. Copy the entire XML content
3. Add as GitHub secret: **AZURE_WEBAPP_PUBLISH_PROFILE**
4. Update workflow to use publish profile authentication

### Workflow change for publish profile:
```yaml
- name: Deploy to Azure Web App
  uses: azure/webapps-deploy@v3
  with:
    app-name: 'spring-boot-demo'
    publish-profile: ${{ secrets.AZURE_WEBAPP_PUBLISH_PROFILE }}
    package: '*.jar'
```

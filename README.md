# Personal Portfolio Website

This project is a personal portfolio website for showcasing my work, experience,
and projects. The goal is to build a full-stack cloud-hosted application with a
Spring Boot backend, an Angular frontend, and Azure-managed infrastructure.

## Planned Architecture

- **Backend:** Spring Boot API packaged as a Docker container.
- **Container hosting:** Azure Container Apps.
- **Container registry:** Docker Hub.
- **Frontend:** Angular application hosted with Azure Static Web Apps.
- **Database:** Azure SQL Database.
- **Secrets management:** Azure Key Vault for application secrets and
  connection settings.

The application will use the Angular frontend for the public portfolio
experience and the Spring Boot backend for API-driven features backed by Azure
SQL Database.

## Backend Configuration

The Spring Boot backend reads its SQL Database connection string from Azure Key
Vault at startup. Store the JDBC URL in Key Vault using the secret name
`spring-datasource-url`.

Example secret value:

```text
jdbc:sqlserver://<sql-server-name>.database.windows.net:1433;database=portfolioapp-db-dev;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;authentication=ActiveDirectoryDefault;
```

For local development, sign in with Azure CLI before starting the backend:

```powershell
az login
```

When deployed to Azure Container Apps, grant the container app's managed
identity access to read secrets from the Key Vault and connect to Azure SQL.

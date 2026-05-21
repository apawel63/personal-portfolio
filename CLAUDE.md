# CLAUDE.md — 12-rule template

These rules apply to every task in this project unless explicitly overridden.
Bias: caution over speed on non-trivial work.

## Rule 1 — Think Before Coding
State assumptions explicitly. Ask rather than guess.
Push back when a simpler approach exists. Stop when confused.

## Rule 2 — Simplicity First
Minimum code that solves the problem. Nothing speculative.
No abstractions for single-use code.

## Rule 3 — Surgical Changes
Touch only what you must. Don't improve adjacent code.
Match existing style. Don't refactor what isn't broken.

## Rule 4 — Goal-Driven Execution
Define success criteria. Loop until verified.
Strong success criteria let Claude loop independently.

## Rule 5 — Use the model only for judgment calls
Use for: classification, drafting, summarization, extraction.
Do NOT use for: routing, retries, deterministic transforms.
If code can answer, code answers.

## Rule 6 — Token budgets are not advisory
Per-task: 4,000 tokens. Per-session: 30,000 tokens.
If approaching budget, summarize and start fresh.
Surface the breach. Do not silently overrun.

## Rule 7 — Surface conflicts, don't average them
If two patterns contradict, pick one (more recent / more tested).
Explain why. Flag the other for cleanup.

## Rule 8 — Read before you write
Before adding code, read exports, immediate callers, shared utilities.
If unsure why existing code is structured a certain way, ask.

## Rule 9 — Tests verify intent, not just behavior
Tests must encode WHY behavior matters, not just WHAT it does.
A test that can't fail when business logic changes is wrong.

## Rule 10 — Checkpoint after every significant step
Summarize what was done, what's verified, what's left.
Don't continue from a state you can't describe back.

## Rule 11 — Match the codebase's conventions, even if you disagree
Conformance > taste inside the codebase.
If you think a convention is harmful, surface it. Don't fork silently.

## Rule 12 — Fail loud
"Completed" is wrong if anything was skipped silently.
"Tests pass" is wrong if any were skipped.
Default to surfacing uncertainty, not hiding it.

## Project Overview

Full-stack personal portfolio site — Angular 21 frontend + Spring Boot 4 REST API + Azure SQL Database. The frontend gracefully degrades to static fallback data if the backend is unreachable (3-second timeout per endpoint).

## Commands

### Frontend (run from `frontend/`)

```powershell
npm start        # Dev server at http://localhost:4200 (proxies /api to :8080)
npm run build    # Production build
npm test         # Angular unit tests
```

### Backend (run from `backend/`)

```powershell
az login                          # Required before first run — fetches Key Vault secret
./mvnw spring-boot:run            # Start API server at http://localhost:8080
./mvnw test                       # Run unit tests
./mvnw package                    # Build JAR
```

The backend reads its SQL connection string from Azure Key Vault (secret name: `spring-datasource-url`) using the Azure CLI credential. `az login` must be completed before starting the backend locally.

## Architecture

### Data Flow

Frontend (`PortfolioContentService`) calls four `/api/*` endpoints in parallel via `forkJoin`. In dev, Angular's proxy (`proxy.conf.json`) forwards `/api/*` to `localhost:8080`. Each request has a 3-second timeout; on failure the service falls back to hardcoded static data.

### Frontend (`frontend/src/app/`)

- **Standalone component model** — no NgModules. `AppComponent` is the single root component handling all rendering, scroll-based nav highlighting, and project detail modals.
- **`PortfolioContentService`** — the only HTTP service. Fetches all four data domains, sorts skills by `category → displayOrder → name`, and returns a single observable of combined portfolio data.
- **`DateRangePipe` / `DateFormatPipe`** — format date strings from the API into human-readable ranges.
- Icons from the database are mapped to FontAwesome/SimpleIcons CSS classes inside `AppComponent`.

### Backend (`backend/src/main/java/com/personalprojects/portfolio/`)

Five entity domains, each with the same layered structure: `controller → service → repository → model`.

| Domain | Controller route |
|---|---|
| About | `GET /api/about` |
| Education | `GET /api/education` |
| Work Experience | `GET /api/work-experience` |
| Projects | `GET /api/projects`, `GET /api/projects/{id}` |
| Skills | `GET /api/skills`, `GET /api/skills/{id}` |

All services are `@Transactional(readOnly = true)`. `GlobalExceptionHandler` centralizes error responses. `DataSourceConfig` pre-warms two HikariCP connections on startup.

### Database Schema

Azure SQL Database, managed manually (Hibernate `ddl-auto=none`). DDL lives in `backend/sql/`. Key relationships:

- `Icon` is a shared lookup table referenced by `About`, `Skill`, `Technology`, and `Link`.
- `Project` has cascade-delete children: `Technology` (M:1) and `Link` (M:1).
- `WorkExperience` has cascade-delete children: `WorkExperienceTask` (ordered by `sortOrder`).

### Deployment Target (planned)

| Component | Azure service |
|---|---|
| Frontend | Azure Static Web Apps |
| Backend | Azure Container Apps (Docker) |
| Database | Azure SQL Database |
| Secrets | Azure Key Vault (managed identity) |

# Workflows

From https://circleci.com/docs/2.0/workflows/

Definition: set of rules for defining a collection of jobs and their run order

workflows:
    - jobs:
        - steps:
            commands

## Tips for advanced configuration

- Move the quickest jobs to the start of the workflow
- Use a 'setup' job at the start of the workflow to do some preflight checks and populate a workspace for all the following jobs

## Example workflow

```yaml
workflows:
    version: 2
    build_accept_deploy:
        jobs:
            - build
            - unit_tests:
                requires: 
                    - build
            - acceptance_tests:
                requires: 
                    - build
            - hold:
                type: approval
                requires:
                    - unit_tests
                    - acceptance_tests
            - deploy
                requires: 
                    - hold

```

## Scheduling a workflow

Add a `triggers` key to the `workflows` configuration.

Use `cron` syntax.

```yaml
workflows:
    version: 2
    commit:
        jobs: 
            - test
            - deploy
    nightly:
        triggers:
            - schedule:
                cron: "0 0 * * *"
                filters:
                    branches:
                        only:
                            - master
                            - beta
        jobs:  
            - coverage
```

## Using Contexts and Filtering in Your Workflows

```yaml
workflows:
    version: 2
    dev_stage_per-prod:
        jobs:
            - test_dev:
                filters:
                    branches:
                        only:
                            - dev
                            - /user-.*/
            - test_stage:
                filters:
                    branches:
                        only: stage
            - test_pre-prod:
                filters:
                    branches:
                        only: /pre-prod(?:-.+)?£/
```
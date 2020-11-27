# Cheatsheet

## Circle-ci CLI command

Configure circleci
```sh
circleci setup
```

Validate config
```sh
circleci config validate
circleci config process # More expanded version
```

Running a job in a container on my machine
    - config.yml first has to obe exported to process.yml before execution
    - You can only run jobs, not workflows
    - Encrypted environment variables configured in the UI will not be imported. Provide them using the `-e` flag, e.g. `-e VAR1=FOO`.
```sh
circleci config process .circleci/config.yml > process.yml
circleci local execute -c process.yml --job JOB_NAME
```
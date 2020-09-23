# Authentication

Different ways:

- Static credentials: not recommended
```hcl 
provider "aws" {
  region     = "us-west-2"
  access_key = "my-access-key"
  secret_key = "my-secret-key"
}
```

- Environment variables
Set `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY`
```hcl
provider "aws" {}
```

- Shared credentials/config file
    - default location: `$HOME/.aws/credentials`
    - specify by providing the `shared_credentials_file` argument or using `AWS_SHARED_CREDENTIALS_FILE` environment variable
    - supports `profile` argument and matching `AWS_PROFILE` environment variable
```hcl 
provider "aws" {
  region                  = "us-west-2"
  shared_credentials_file = "/Users/tf_user/.aws/creds"
  profile                 = "customprofile"
}
```

- CodeBuild, ECS, EKS Roles
    - If running Terraform on CodeBuild or ECS and have configured an IAM Task Role
    - If running in EKS and have configured IAM Roles for Service Accounts ()IRSA
, Terraform will use the pod's role

- EC2 Instance Metadata Service
If you are running Terraform from an EC2 instance with IAM instance profile using IAM Role, Terraform just ask the metadata API endpoint for credentials

## Assuming Role

If procided with a role ARN, Terraform will attempt to assume this role using the supplied credentials.

```hcl
provider "aws" {
  assume_role {
    role_arn     = "arn:aws:iam::ACCOUNT_ID:role/ROLE_NAME"
    session_name = "SESSION_NAME"
    external_id  = "EXTERNAL_ID"
  }
}
```
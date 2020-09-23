# Project initialisation

## Backend

By default backend is stored locally in a JSON file on disk

Specify a remote backend to store state file

You can specify state locking if possible depending on the backend type.

### For AWS:

Use S3 for state storage and DynamoDB for state locking

```hcl
terraform {
    backend "s3" {
        bucket = "mybucket"
        key    = "path/to/my/key"
        region = "us-east-1"
        dynamodb_table = "my-terraform-state-tfstate-lock" # for state locking
    }
}
```

Terraform will need the appropriate permissions:
- For writing the state in the S3 bucket:
    - s3:ListBucket on arn:aws:s3:::mybucket
    - s3:GetObject on arn:aws:s3:::mybucket/path/to/my/key
    - s3:PutObject on arn:aws:s3:::mybucket/path/to/my/key
- For state locking on the DynamoDB table:
    - dynamodb:GetItem
    - dynamodb:PutItem
    - dynamodb:DeleteItem

## For GCP

Use GCS for state storage

```hcl
terraform {
  backend "gcs" {
    bucket  = "tf-state-prod"
    prefix  = "terraform/state"
  }
}
```

## Data Configuration

To make use of the S3 remote state in another config, use the `terraform_remote_state` data source:

For AWS 
```hcl 
data "terraform_remote_state" "network" {
  backend = "s3"
  config = {
    bucket = "terraform-state-prod"
    key    = "network/terraform.tfstate"
    region = "us-east-1"
  }
}
```

For GCP
```hcl 
data "terraform_remote_state" "foo" {
  backend = "gcs"
  config = {
    bucket  = "terraform-state"
    prefix  = "prod"
  }
}
```

## Provider resources

Create a provider 
```hcl
{
    provider = "aws" {
        version = "~> 3.0"
        region = "eu-west-1"
    }
}
```


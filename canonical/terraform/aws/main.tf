provider "aws" {
    version = "~> 3.0"
    region = "eu-west-1"
}

resource "aws_vpc" "example_vpc" {
    cidr_block = "10.0.0.0/16"
}
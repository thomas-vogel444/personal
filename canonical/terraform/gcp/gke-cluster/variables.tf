variable "project_name" {
  type = string
}

variable "region" {
  type = string
}

variable "network_name" {
  type = string
}

variable "public_subnet_cidr" {
  type = string
}

variable "private_subnet_cidr" {
  type = string
}

variable "internal_subnet_cidr" {
  type = string
}

variable "k8s_master_cidr" {
  type = string
}

variable "k8s_pod_cidr" {
  type = string
}

variable "k8s_service_cidr" {
  type = string
}
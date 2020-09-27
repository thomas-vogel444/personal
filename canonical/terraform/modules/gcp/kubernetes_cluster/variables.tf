variable "network" {
  type = string
}

variable "subnet" {
  type = string
}

variable "region" {
  type = string
}

variable "master_cidr" {
  type = string
}

variable "pod_cidr" {
  type = string
}

variable "service_cidr" {
  type = string
}

variable "network_tags" {
  type = list(string)
}
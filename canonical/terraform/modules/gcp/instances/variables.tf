variable "instance_name" {
  type = string
}

variable "machine_type" {
  type = string
}

variable "instance_zone" {
  type = string
}

variable "subnet_name" {
  type = string
}

variable "service_account_email" {
  type = string
  default = ""
}

variable "startup_script" {
  type = string
  default = ""
}

variable "public_ip" {
  type = string
  default = ""
}

variable "network_tags" {
  type = list(string)
  default = []
}
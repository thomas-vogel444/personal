variable "network_name" {
  description = "Name of the network"
  type = string
}

variable "public_cidr" {
  description = "CIDR range of the public subnet"
  type = string
}

variable "private_cidr" {
  description = "CIDR range of the private subnet"
  type = string
}

variable "internal_cidr" {
  description = "CIDR range of the internal subnet"
  type = string
}
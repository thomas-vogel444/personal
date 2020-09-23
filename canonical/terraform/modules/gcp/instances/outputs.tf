locals {
  access_config = google_compute_instance.instance.network_interface.0.access_config
}

output "external_ip" {
  value = length(local.access_config) != 0 ? local.access_config.0.nat_ip : ""
}

output "internal_ip" {
  value = google_compute_instance.instance.network_interface.0.network_ip
}
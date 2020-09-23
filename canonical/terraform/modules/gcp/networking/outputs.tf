output "public_subnet_name" {
  value = google_compute_subnetwork.public_subnet.name
}

output "private_subnet_name" {
  value = google_compute_subnetwork.private_subnet.name
}

output "internal_subnet_name" {
  value = google_compute_subnetwork.internal_subnet.name
}

output "bastion_external_ip" {
  value = google_compute_address.bastion_external_ip.address
}
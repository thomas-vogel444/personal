resource "google_compute_address" "bastion_external_ip" {
  name = "bastion-external-ip"
}

resource "google_compute_instance" "instance" {
  depends_on = [google_compute_subnetwork.private_subnet]

  name = "bastion"
  machine_type = "e2-micro"
  zone = "europe-west2-b"

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-9"
    }
  }

  network_interface {
    subnetwork = google_compute_subnetwork.private_subnet.name

    access_config {
      nat_ip = google_compute_address.bastion_external_ip.address
    }
  }

  allow_stopping_for_update = true

  tags = ["ssh-egress"]
}
resource "google_compute_instance" "instance" {
  name = var.instance_name
  machine_type = var.machine_type
  zone = var.instance_zone

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-9"
    }
  }

  network_interface {
    subnetwork = var.subnet_name

    dynamic "access_config" {
      for_each = var.public_ip != "" ? [var.public_ip] : []
      content {
        nat_ip = access_config.value
      }
    }
  }

  dynamic "service_account" {
    for_each = var.service_account_email != "" ? [var.service_account_email] : []
    content {
      scopes = ["https://www.googleapis.com/auth/cloud-platform"]
      email = service_account.value
    }
  }

  metadata_startup_script = var.startup_script
  allow_stopping_for_update = true

  tags = var.network_tags

//  scheduling {
//    preemptible = true
//  }
}


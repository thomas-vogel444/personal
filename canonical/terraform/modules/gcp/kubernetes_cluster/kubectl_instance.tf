//************************************************************
// kubectl instance
resource "google_service_account" "member" {
  account_id = "kubectl"
}

resource "google_project_iam_member" "project" {
  depends_on = [google_service_account.member]

  role = "roles/container.developer"
  member = "serviceAccount:${google_service_account.member.email}"
}

resource "google_compute_instance" "kubectl" {
  name = "kubectl"
  machine_type = "e2-micro"
  zone = "europe-west2-b"

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-9"
    }
  }

  network_interface {
    subnetwork = var.subnet
  }

  service_account {
    scopes = ["https://www.googleapis.com/auth/cloud-platform"]
    email = google_service_account.member.email
  }

  metadata_startup_script = <<EOF
sudo apt-get update
sudo apt-get install kubectl
gcloud container clusters get-credentials personal --region europe-west2
alias k='kubectl'
EOF

  allow_stopping_for_update = true

  tags = ["ssh-ingress", "http-https-ingress", "http-https-egress"]

  //  scheduling {
  //    preemptible = true
  //  }
}



























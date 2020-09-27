resource "google_compute_firewall" "allow_ssh_ingress" {
  depends_on = [google_compute_network.vpc]

  name = "allow-ssh-ingress"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports = ["22"]
  }

  direction = "INGRESS"
  source_ranges = ["0.0.0.0/0"]
  source_tags = ["ssh-ingress"]
}

resource "google_compute_firewall" "allow_ssh_egress" {
  depends_on = [google_compute_network.vpc]

  name = "allow-ssh-egress"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports = ["22"]
  }

  direction = "EGRESS"
  target_tags = ["ssh-egress"]
}
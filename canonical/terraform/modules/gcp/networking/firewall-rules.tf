resource "google_compute_firewall" "bastion_ssh_ingress" {
  depends_on = [google_compute_network.vpc]

  name = "bastion-allow-ssh-ingress"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports = ["22"]
  }

  direction = "INGRESS"
  source_ranges = ["83.216.93.248"]
}

resource "google_compute_firewall" "ssh_ingress" {
  depends_on = [google_compute_network.vpc]

  name = "allow-ssh-ingress"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports = ["22"]
  }

  direction = "INGRESS"
  source_tags = ["ssh-egress"]
}

resource "google_compute_firewall" "ssh_egress" {
  depends_on = [google_compute_network.vpc]

  name = "allow-ssh-egress"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports = ["22"]
  }

  direction = "EGRESS"
  target_tags = ["ssh-ingress"]
}

resource "google_compute_firewall" "http-https-egress" {
  depends_on = [google_compute_network.vpc]

  name = "http-https-egress"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports = ["80", "443"]
  }

  direction = "EGRESS"
}

resource "google_compute_firewall" "http-https-ingress" {
  depends_on = [google_compute_network.vpc]

  name = "http-https-ingress"
  network = google_compute_network.vpc.name

  allow {
    protocol = "tcp"
    ports = ["80", "443"]
  }

  direction = "EGRESS"
}
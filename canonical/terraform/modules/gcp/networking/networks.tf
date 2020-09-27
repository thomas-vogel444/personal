resource "google_compute_network" "vpc" {
  name = var.network_name
  auto_create_subnetworks = false
}

// ***************************************************************
// Subnets
resource "google_compute_subnetwork" "public_subnet" {
  depends_on = [google_compute_network.vpc]

  name = "${var.network_name}-public-subnet"
  network = google_compute_network.vpc.name
  ip_cidr_range = var.public_cidr
}

resource "google_compute_subnetwork" "private_subnet" {
  depends_on = [google_compute_network.vpc]

  name = "${var.network_name}-private-subnet"
  network = google_compute_network.vpc.name
  ip_cidr_range = var.private_cidr
}

resource "google_compute_subnetwork" "internal_subnet" {
  depends_on = [google_compute_network.vpc]

  name = "${var.network_name}-internal-subnet"
  network = google_compute_network.vpc.name
  ip_cidr_range = var.internal_cidr
}
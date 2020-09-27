// ***************************************************************
// NAT Gateway
resource "google_compute_router" "router" {
  depends_on = [google_compute_network.vpc]

  name    = "tom-router"
  network = google_compute_network.vpc.name
}

resource "google_compute_router_nat" "nat" {
  depends_on = [google_compute_subnetwork.private_subnet]

  name                               = "my-router-nat"
  router                             = google_compute_router.router.name
  nat_ip_allocate_option             = "AUTO_ONLY"

  source_subnetwork_ip_ranges_to_nat = "LIST_OF_SUBNETWORKS"
  subnetwork {
    name                    = google_compute_subnetwork.private_subnet.name
    source_ip_ranges_to_nat = ["ALL_IP_RANGES"]
  }
}
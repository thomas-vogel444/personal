terraform {
  backend "gcs" {
//    project = "tom-infrastructure"
    bucket = "tomvogel01-infrastructure"
    prefix = "terraform/state"
  }
}
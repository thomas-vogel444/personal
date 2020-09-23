resource "google_service_account" "member" {
  account_id = var.service_account_name
}

resource "google_project_iam_member" "project" {
  depends_on = [google_service_account.member]

  role = var.role
  member = "serviceAccount:${google_service_account.member.email}"
}
# The tfenv CLI tool

From https://github.com/tfutils/tfenv

Used for managing Terraform versions

## Usage

Installing/uninstalling terraform versions
```bash
tfenv install # requires a .terraform-version file
tfenv install 0.13.2
tfenv install latest
tfenv install min-required

tfenv uninstall 0.7.0
tfenv uninstall latest
tfenv uninstall latest:^0.8 # regex

cat .terraform-version
 # Output: 0.6.16
```

Use a particular version of terraform
```bash
tfenv use
tfenv use min-required
tfenv use 0.7.0
tfenv use latest
tfenv use latest:^0.8
```


Listing available terraform versions
```bash
tfenv list
tfenv list-remote
```

## Terraform-version file

If you put a .terraform-version file on your project root, or in your home directory, tfenv detects it and uses the version written in it.

```bash
$ cat .terraform-version
0.6.16

$ terraform --version
Terraform v0.6.16

Your version of Terraform is out of date! The latest version
is 0.7.3. You can update by downloading from www.terraform.io

$ echo 0.7.3 > .terraform-version

$ terraform --version
Terraform v0.7.3

$ echo latest:^0.8 > .terraform-version

$ terraform --version
Terraform v0.8.8
```
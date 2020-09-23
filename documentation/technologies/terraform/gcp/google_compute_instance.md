# Google Compute Instance

Requires:
- `name`
- `machine_type`
- `zone`
- `book_disk block`
- `network_interface_block`

For network tags
- set `tags` as a list of network tags

Specify the image
- set `boot_disk.initialize_params.image`

Specify a subnet
- set `network_interface.subnetwork`

Setting an external IP
- set the `access_config` block

Making the instance Preemptible
- set the `scheduling.preemptible` flag to true.
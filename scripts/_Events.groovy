eventCompileStart = {kind ->
	bowerInstallPackages()
}

private void bowerInstallPackages() {
	def proc
	if (System.properties['os.name'].toLowerCase().contains('windows')) {
		proc = ['cmd', '/c', 'bower install'].execute()
	} else {
		proc = 'bower install'.execute()
	}

	proc.waitFor()
	if(proc.exitValue() != 0){
		println 'An Error Occurred'
	} else {
		println 'Bower Packages Updated!'
	}
}

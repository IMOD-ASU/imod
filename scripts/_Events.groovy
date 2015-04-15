eventCompileStart = {kind ->
	bowerInstallPackages()
}

private void bowerInstallPackages(){
	def proc = 'bower install'.execute()  // execute default task to load dependencies from local cache.
	proc.waitFor()
	if(proc.exitValue() != 0){
		println 'An Error Occurred'
	} else {
		println 'Bower Packages Updated!'
	}
}

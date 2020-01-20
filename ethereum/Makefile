dir="src"

charm: foo bar
foo:
	docker run \
		-v $(PWD)/$(dir):/srv/gitbook \
		-v $(PWD)/html:/srv/html \
		fellah/gitbook:3.2.0 \
		gitbook init


bar:
	docker run \
		-v $(PWD)/$(dir):/srv/gitbook \
		-v $(PWD)/html:/srv/html \
		fellah/gitbook:3.2.0 \
		gitbook install

build:
	docker run \
		-v $(PWD)/$(dir):/srv/gitbook \
		-v $(PWD)/html:/srv/html \
		fellah/gitbook:3.2.0 \
		gitbook build . /srv/html --log=debug --debug


clean:
	rm -rf src/_book
	rm -rf html

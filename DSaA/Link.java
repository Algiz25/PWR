public class Link implements Comparable<Link>{
	public String ref;
	public int weight;
	public Link(String ref) {
		this.ref=ref;
		weight=1;
	}
	public Link(String ref, int weight) {
		this.ref=ref;
		this.weight=weight;
	}
	@Override
	public boolean equals(Object obj) {
		Link newLink=(Link)obj;
        return newLink.ref.equals(this.ref) && newLink.weight == this.weight;
    }
	@Override
	public String toString() {
		return ref+"("+weight+")";
	}

	public String getRef() {
		return ref;
	}

	@Override
	public int compareTo(Link another) {
		return this.ref.compareTo(another.ref);
	}
}


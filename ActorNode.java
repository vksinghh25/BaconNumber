public class ActorNode {

    String name;

    // constructor that takes the Actor Name
    ActorNode(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object otherActorNode) {
        if (!(otherActorNode instanceof ActorNode))
            return false;
        else
            return this.name.equalsIgnoreCase(((ActorNode) otherActorNode).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

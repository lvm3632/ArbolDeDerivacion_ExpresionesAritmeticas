public class Nodo {
    protected String value;
    protected Nodo left;
    protected Nodo right;
    protected Nodo mid;
    Nodo(String value) {
        this.value = value;
        left = null;
        mid = null;
        right = null;

    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Nodo getLeft() {
        if(this.left == null)return new Nodo("");
        return left;
    }
    public Nodo getLeftNull() {
        return left;
    }
    public void setLeft(Nodo left) {
        this.left = left;
    }
    public Nodo getRight() {
        if(this.right == null)return new Nodo("");
        return right;
    }
    public Nodo getRightNull() {
        return right;
    }
    public void setRight(Nodo right) {
        this.right = right;
    }
    public void setMid(Nodo mid) {
        this.mid = mid;
    }
    public Nodo getMid() {
        if(this.mid == null)return new Nodo("");
        return mid;
    }
    public Nodo addLeftLeft(String valor) {
        if(this.left.left.left == null)return new Nodo(valor);
        return this.left.left.left;
    }
    public Nodo addMidMid(String valor) {
        if(this.mid.mid == null)return new Nodo(valor);
        return this.mid.mid;
    }
    public Nodo addRightRight(String valor) {
        if(this.right.right == null)return new Nodo(valor);
        return this.right.right;
    }
    public Nodo getLeftLeft(){
        return this.left.left;
    }
    public Nodo getMidMid(){
        return this.mid.mid;
    }
    public Nodo getRightRight(){
        return this.right.right;
    }
    public Nodo getMidNull() {
        return mid;
    }
    @Override
    public String toString() {
        return "Nodo {" +
                "value='" + value + '\'' +
                ", left=" + left +
                ", mid=" + mid +
                ", right=" + right +
                '}';
    }
}

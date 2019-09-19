/** A class that represents a path via pursuit curves. */
public class Path {

    // TODO
    public Point curr;
    public Point next;

    public Path(double x, double y) {
      next = new Point(x, y);
      curr = new Point();
    }

    public void iterate(double dx, double dy) {
      curr = next;
      next = new Point(next.x + dx, next.y + dy);
    }


}

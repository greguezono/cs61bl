import java.lang.Math;

public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
      xxPos = xP;
      yyPos = yP;
      xxVel = xV;
      yyVel = yV;
      mass = m;
      imgFileName = img;
    }

    public Planet(Planet p) {
      xxPos = p.xxPos;
      yyPos = p.yyPos;
      xxVel = p.xxVel;
      yyVel = p.yyVel;
      mass = p.mass;
      imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
      double x = p.xxPos - xxPos;
      double y = p.yyPos - yyPos;
      x = x*x;
      y = y*y;
      return Math.sqrt(x+y);
    }

    public double calcForceExertedBy(Planet p) {
      double r = calcDistance(p);
      return (G * mass * p.mass) / (r*r);
    }

    public double calcForceExertedByX(Planet p) {
      double r = calcDistance(p);
      double F = calcForceExertedBy(p);
      double dx = p.xxPos - xxPos;
      return (F * dx) / r;
    }

    public double calcForceExertedByY(Planet p) {
      double r = calcDistance(p);
      double F = calcForceExertedBy(p);
      double dy = p.yyPos- yyPos;
      return (F * dy) / r;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
      double nfx = 0.0;
      for (Planet p : planets) {
        if (p.equals(this)) {
          continue;
        }
        nfx += calcForceExertedByX(p);
      }
      return nfx;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
      double nfy = 0.0;
      for (Planet p : planets) {
        if (p.equals(this)) {
          continue;
        }
        nfy += calcForceExertedByY(p);
      }
      return nfy;
    }

    public void update(double dt, double fX, double fY) {
      double ax = fX / mass;
      double ay = fY / mass;
      xxVel = xxVel + dt * ax;
      yyVel = yyVel + dt * ay;
      xxPos = xxPos + dt*xxVel;
      yyPos = yyPos + dt*yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos,yyPos, "/images/" + imgFileName);
    }
}

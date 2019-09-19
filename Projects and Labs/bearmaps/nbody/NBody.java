public class NBody {

    public static double readRadius(String filename) {
        In in = new In(filename);
        int first = in.readInt();
        double r = in.readDouble();
        return r;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numPlanets = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[numPlanets];
        int i = 0;
        while (i < numPlanets) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
            planets[i] = p;
            i++;
        }
        return planets;
    }

    public static void main(String[] args) {
        //set up args in Command Line
     double time = 0.0;
     double T = Double.parseDouble(args[0]);
     double dt = Double.parseDouble(args[1]);
     String filename = args[2];

     Planet[] planets = readPlanets(filename);
     double radius = readRadius(filename);
     double[] xForces = new double[planets.length];
     double[] yForces = new double[planets.length];
     // draw background
     StdDraw.enableDoubleBuffering();
     String imagetoDraw = "/images/starfield.jpg";
     StdDraw.setScale(-radius, radius);
     StdDraw.clear();

     // calculate Net Forces
    while (time != T) {
         // draw planets
         for (int i = 0; i < planets.length; i++) {
             double nfx = planets[i].calcNetForceExertedByX(planets);
             double nfy = planets[i].calcNetForceExertedByY(planets);
             xForces[i] = nfx;
             yForces[i] = nfy;
         }
         //Update planets
         for (int i = 0; i < planets.length; i++) {
             planets[i].update(dt, xForces[i], yForces[i]);
         }

     //draw everything and increment time
        StdDraw.picture(0.0,0.0, imagetoDraw);

        for (Planet p : planets) {
            p.draw();
        }

        StdDraw.show();
        StdDraw.pause(10);
        time += dt;
     }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i += 1) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }

    }
}

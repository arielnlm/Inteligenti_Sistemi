public class Tacka {
        private int x,y,visina;

        public Tacka(int x, int y, int visina){
            this.x = x;
            this.y = y;
            this.visina = visina;
        }

        public int getX(){return x;}
        public int getY(){return y;}
        public int getVisina(){return visina;}

        public int manhattan(Tacka t){
            return Math.abs(t.getX() - x) + Math.abs(t.getY() - y);
        }

        public int visinskaRazlika(Tacka t){
            return t.getVisina() - visina;
        }

        @Override
        public boolean equals(Object o){
            Tacka t = (Tacka)o;
            return t.getX() == x && t.getY() == y && t.getVisina() == visina;
        }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public String toString(){
            return "(y,x)=(" + y + "," + x + ")";
    }
}

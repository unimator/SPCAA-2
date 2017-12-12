import java.util.concurrent.CyclicBarrier;

public class NonLinearImplicit implements Problem {

    private static final double beta = 1;

    private double F(double t) {
        return 2 * beta * Math.PI *  Math.cos(2 * Math.PI * beta * t);
    }

    @Override
    public Production makeA1(Vertex v, double h, double dt, double t, CyclicBarrier barrier) {
        return new ABase(v, h, dt, t, barrier) {
            @Override
            Vertex apply(Vertex v) {
                v.m_a[1][1] = h * 0.5 + dt * beta;
                v.m_a[1][2] = 0;
                v.m_a[2][1] = h * 0.5 - dt * beta;
                v.m_a[2][2] = 0;

                v.m_b[1] = h * 0.5 * m_vertex.m_x[1] + dt * h * 0.5 * F(t);
                v.m_b[2] = h * 0.5 * m_vertex.m_x[1] + dt * h * 0.5 * F(t);
                return v;
            }
        };
    }

    @Override
    public Production makeA(Vertex v, double h, double dt, double t, CyclicBarrier barrier) {
        return new ABase(v, h, dt, t, barrier) {
            @Override
            Vertex apply(Vertex v) {
                v.m_a[1][1] = h * 0.5 + dt * beta;
                v.m_a[1][2] = 0;
                v.m_a[2][1] = h * 0.5 - dt * beta;
                v.m_a[2][2] = 0;

                v.m_b[1] = h * 0.5 * m_vertex.m_x[1] + dt * h * 0.5 * F(t);
                v.m_b[2] = h * 0.5 * m_vertex.m_x[1] + dt * h * 0.5 * F(t);
                return v;
            }
        };
    }

    @Override
    public Production makeAN(Vertex v, double h, double dt, double t, CyclicBarrier barrier) {
        return new ABase(v, h, dt, t, barrier) {
            @Override
            Vertex apply(Vertex v) {
                v.m_a[1][1] = h * 0.5 + dt * beta;
                v.m_a[1][2] = 0;
                v.m_a[2][1] = h * 0.5 - dt * beta;
                v.m_a[2][2] = h * 0.5 + dt * beta;

                v.m_b[1] = h * 0.5 * m_vertex.m_x[1] + dt * h * 0.5 * F(t);
                v.m_b[2] = h * 0.5 * (m_vertex.m_x[1] + m_vertex.m_x[2]) + dt * h * 0.5 * F(t);
                return v;
            }
        };
    }
}

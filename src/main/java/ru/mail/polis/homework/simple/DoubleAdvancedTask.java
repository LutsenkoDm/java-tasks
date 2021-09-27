package ru.mail.polis.homework.simple;

/**
 * Возможно вам понадобится класс Math с его методами. Например, чтобы вычислить квадратный корень, достаточно написать
 * Math.sqrt(1.44)
 * Чтобы увидеть все методы класса Math, достаточно написать Math. и среда вам сама покажет возможные методы.
 * Для просмотра подробной документации по выбранному методу нажмите Ctrl + q
 */
public class DoubleAdvancedTask {

    /**
     * Вывести три корня кубического уравнения через запятую: a * x ^ 3 + b * x ^ 2 + c * x + d = 0;
     * Вывод менять не нужно, надо только посчитать x1, x2 и x3, где x1 >= x2 >= x3
     * Считаем, что все три корня вещественные.
     * <p>
     * Если используете какой-то конкретный способ, напишите какой.
     * Пример: (1, -4, -7, 10) -> "-2.0, 1.0, 5.0"
     */
    public static String equation(int a, int b, int c, int d) {
        double[] x = new double[3];
        if (d == 0) {
            //Делим на аргумент и решаем как квадратное уравнение + нулевой корень
            double discriminantSqrt = Math.sqrt(b * b - 4 * a * c);
            x[0] = (-b + discriminantSqrt) / (2 * a);
            x[1] = (-b - discriminantSqrt) / (2 * a);
            x[2] = 0.0;
        } else {
            //Тригонометрическая формула Виета: https://ru.wikipedia.org/wiki/Тригонометрическая_формула_Виета
            double reducedB = (double) b / a;
            double reducedC = (double) c / a;
            double reducedD = (double) d / a;
            double q = (reducedB * reducedB - 3.0 * reducedC) / 9.0;
            double r = (2.0 * reducedB * reducedB * reducedB - 9.0 * reducedB * reducedC + 27.0 * reducedD) / 54.0;
            double fi = 1.0 / 3.0 * Math.acos(r / Math.sqrt(q * q * q));
            x[0] = -2.0 * Math.sqrt(q) * Math.cos(fi) - reducedB / 3.0;
            x[1] = -2.0 * Math.sqrt(q) * Math.cos(fi + 2.0 * Math.PI / 3.0) - reducedB / 3.0;
            x[2] = -2.0 * Math.sqrt(q) * Math.cos(fi - 2.0 * Math.PI / 3.0) - reducedB / 3.0;
        }
        return x[2] + ", " + x[1] + ", " + x[0];
    }

    /**
     * Нужно посчитать расстояние, между двумя прямыми
     * Примеры: (1, 1, 2, -1) -> 0
     * (0, 1, 0, 5) -> 4
     */
    public static float length(double a1, double b1, double a2, double b2) {
        if (a1 == a2) {
            return (float) (Math.abs(b2 - b1) * Math.cos(Math.atan(a1)));
        }
        return 0;
    }

    /**
     * Даны три точки в пространстве (x1, y1, z1) , (x2, y2, z2) и (x3, y3, z3). Вам нужно найти Z координату
     * четвертой точки (x4, y4, z4), которая находится на той же плоскости что и первые три.
     * (0, 0, 1,
     * 1, 1, 1,
     * 10, 100, 1,
     * 235, -5) -> 1
     */
    public static double surfaceFunction(int x1, int y1, int z1,
                                         int x2, int y2, int z2,
                                         int x3, int y3, int z3,
                                         int x4, int y4) {
        /* |(x4 - x1) (y4 - y1) (z4 - z1)|
           |(x2 - x1) (y2 - y1) (z2 - z1)| = 0
           |(x3 - x1) (y3 - y1) (z3 - z1)| */
        double knownDeterminantSum =
                (x4 - x1) * (y2 - y1) * (z3 - z1)
                        + (y4 - y1) * (z2 - z1) * (x3 - x1)
                        - (x4 - x1) * (z2 - z1) * (y3 - y1)
                        - (y4 - y1) * (x2 - x1) * (z3 - z1);
        return -knownDeterminantSum / ((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1)) + z1;
    }
}

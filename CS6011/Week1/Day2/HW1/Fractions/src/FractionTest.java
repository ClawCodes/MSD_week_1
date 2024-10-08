import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class FractionTest {
    @Test
    public void testDefaultConstructor(){
        Fraction fraction = new Fraction();
        Assertions.assertEquals(0, fraction.numerator);
        Assertions.assertEquals(1, fraction.denominator);
    }

    @Test
    public void testConstructor(){
        // Testing constructor also tests Reduce()
        Fraction preReduced = new Fraction(1, 4);
        Assertions.assertEquals(preReduced.numerator, 1);
        Assertions.assertEquals(preReduced.denominator, 4);

        Fraction postReduced = new Fraction(2, 4);
        Assertions.assertEquals(postReduced.numerator, 1);
        Assertions.assertEquals(postReduced.denominator, 2);

        Fraction negativeDenom = new Fraction(2, -4);
        Assertions.assertEquals(negativeDenom.numerator, -1);
        Assertions.assertEquals(negativeDenom.denominator, 2);

        Fraction bothNegative = new Fraction(-2, -4);
        Assertions.assertEquals(bothNegative.numerator, 1);
        Assertions.assertEquals(bothNegative.denominator, 2);
    }

    @Test
    public void testConstructorThrows(){
        // Assertions using try/catch
        try{
            new Fraction(1, 0);
        } catch (ArithmeticException e){
            Assertions.assertEquals("A fraction cannot have a denominator of 0 as you cannot divide by zero", e.getMessage());
        }

        // Assertions using Junit
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            new Fraction(1, 0);
        });

        String expectedMesage = "A fraction cannot have a denominator of 0 as you cannot divide by zero";

        Assertions.assertEquals(expectedMesage, exception.getMessage());
    }

    @Test
    public void testToString(){
        Fraction positive = new Fraction(1, 4);
        Assertions.assertEquals("1/4", positive.toString());

        Fraction negative = new Fraction(2, -4);
        Assertions.assertEquals("-1/2", negative.toString());

        Fraction default_ = new Fraction();
        Assertions.assertEquals("0/1", default_.toString());
    }

    @Test
    public void testPlus(){
        Fraction positive = new Fraction(1, 4);
        Fraction negative = new Fraction(-2, 4);

        Assertions.assertEquals("-1/4", positive.plus(negative).toString());
        Assertions.assertEquals("1/2", positive.plus(positive).toString());
        Assertions.assertEquals("-1/1", negative.plus(negative).toString());
    }

    @Test
    public void testMinus(){
        Fraction positive = new Fraction(1, 4);
        Fraction negative = new Fraction(-2, 4);

        Assertions.assertEquals("3/4", positive.minus(negative).toString());
        Assertions.assertEquals("0/1", positive.minus(positive).toString());
        Assertions.assertEquals("0/1", negative.minus(negative).toString());
    }

    @Test
    public void testTimes(){
        Fraction positive = new Fraction(1, 4);
        Fraction negative = new Fraction(-2, 4);

        Assertions.assertEquals("-1/8", positive.times(negative).toString());
        Assertions.assertEquals("1/16", positive.times(positive).toString());
        Assertions.assertEquals("1/4", negative.times(negative).toString());
    }

    @Test
    public void testDividedBy(){
        Fraction positive = new Fraction(1, 4);
        Fraction negative = new Fraction(-2, 4);

        Assertions.assertEquals("-1/2", positive.dividedBy(negative).toString());
        Assertions.assertEquals("1/1", positive.dividedBy(positive).toString());
        Assertions.assertEquals("1/1", negative.dividedBy(negative).toString());
    }

    @Test
    public void testReciprocal(){
        Fraction oneHalf = new Fraction(1, 2);
        Assertions.assertEquals("2/1", oneHalf.reciprocal().toString());

        Fraction negative = new Fraction(-2, 4);
        Assertions.assertEquals("-2/1", negative.reciprocal().toString());
    }

    @Test
    public void testToDouble(){
        Fraction positive = new Fraction(1, 4);
        Fraction negative = new Fraction(-1, 4);
        Fraction default_ = new Fraction();

        Assertions.assertEquals(0.25, positive.toDouble());
        Assertions.assertEquals(-0.25, negative.toDouble());
        Assertions.assertEquals(0.0, default_.toDouble());
    }

    @Test
    public void testCompareTo(){
        // Less than
        Fraction oneHalf = new Fraction(1, 4);
        Fraction oneThird = new Fraction(1, 3);

        Assertions.assertEquals(-1, oneHalf.compareTo(oneThird));
        Assertions.assertEquals(1, oneThird.compareTo(oneHalf));
        Assertions.assertEquals(0, oneHalf.compareTo(oneHalf));

        // Assert nullpointer exception raised
        try{
            oneThird.compareTo(null);
        } catch (Exception e){
            Assertions.assertInstanceOf(NullPointerException.class, e);
        }
    }

    @Test
    public void testSortFracArray(){
        ArrayList<Fraction> fractions = new ArrayList<>();

        Fraction zero = new Fraction(0,1);
        Fraction one = new Fraction(1,1);
        Fraction oneHalf = new Fraction(1, 2);

        fractions.add(oneHalf);
        fractions.add(one);
        fractions.add(zero);

        Collections.sort(fractions);

        Assertions.assertEquals(zero.toString(), fractions.get(0).toString());
        Assertions.assertEquals(oneHalf.toString(), fractions.get(1).toString());
        Assertions.assertEquals(one.toString(), fractions.get(2).toString());
    }

    @Test
    public void runAllTests(){
        this.testDefaultConstructor();
        this.testConstructor();
        this.testToString();
        this.testPlus();
        this.testMinus();
        this.testTimes();
        this.testDividedBy();
        this.testReciprocal();
        this.testToDouble();
        this.testCompareTo();
        this.testSortFracArray();
        System.out.println("All tests passed!");
    }
}
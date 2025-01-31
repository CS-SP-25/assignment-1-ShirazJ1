public class SalesTaxCalculator {

    public static void main(String[] args) throws Exception {
        // storing command line arguments in variables.
        String stateName = args[0];
        Double sale = Double.parseDouble(args[1]);
        // conditions to check which state is given
        if (stateName.equalsIgnoreCase("Alaska")) {
            // initializing the state object depending on the user input.
            State alaska = new Alaska(stateName);
            // prints the output.
            alaska.showTax(sale);
        }
        else if (stateName.equalsIgnoreCase("Indiana")) {
            // initialzing the Indiana state object to match user's input.
            State indiana = new Indiana(stateName);
            // prints the outpu
            indiana.showTax(sale);
        }
        else if (stateName.equalsIgnoreCase("Hawaii")) {
            // initialzing the Indiana state object to match user's input.
            State hawaii = new Hawaii(stateName);
            // prints the output
            hawaii.showTax(sale);
        }
        // throws an exception if a state other than IN or AL or HW is given
        else {
            throw new Exception("Invalid State");
        }

    }
}

interface SalesTaxBehavior {
    // compute function prototype 
    public double compute(double value);
}

// NoTax class dependent on salestaxbehavior interface.
class NoTax implements SalesTaxBehavior {
    // overriding compute function
    // to match the NoTax implementation 
    @Override
    public double compute(double value) {
        return 0.00;
    }
}

class SevenPercent implements SalesTaxBehavior {
    // overriding compute function
    // to match SevenPercent implementation
    @Override
    public double compute(double value) {
        return Math.round(value * 0.07);
    }
}

// New 4.5% sale tax
class FourPointFivePercent implements SalesTaxBehavior {
    @Override
    public double compute(double value) {
        return Math.round(((value * 0.045) * 100.00)) / 100.0;
    }
}

// abstract class to extend Alaska and Indiana.
abstract class State {
    // private variable
    private String name;
    // getter
    public String getName() {
        return this.name;
    }
    //setter
    public void setName(String newName) {
        this.name = newName;
    }
    // base showTax method that gets overriden by derived classes.
    public void showTax(Double value) {
        System.out.println("State Tax");
    }
}

class Alaska extends State {
    // NoTax behavior to match Alaska's tax rate.
    SalesTaxBehavior taxBehavior = new NoTax();
    // constructor
    public Alaska(String name) {
        //sets the state name to the given name
        setName(name);
    }
    
    //overrides the showTax function to match the Alaska state class.
    @Override
    public void showTax(Double value) {
        System.out.println("The sales tax on $" + value + " in " + getName() + " is $" + taxBehavior.compute(value) + ".");
    }
}

class Indiana extends State {
    //SevenPercent tax bhavior to match Indiana tax rate.
    SalesTaxBehavior taxBehavior = new SevenPercent();
    // constructor, sets the name to given name.
    public Indiana(String name) {
        setName(name);
    }

    //overrides the showTax function to match Indiana state class.
    @Override
    public void showTax(Double value) {
        System.out.println("The sales tax on $" + value + " in " + getName() + " is $" + taxBehavior.compute(value) + ".");
    }
}

// adding Hawaii state 
class Hawaii extends State {
    SalesTaxBehavior taxBehavior = new FourPointFivePercent();
    public Hawaii(String name) {
        setName(name);
    }
    
    //overriding showTax method to display specific name and tax.
    @Override
    public void showTax(Double value) {
        System.out.println("The sales tax on $" + value + " in " + getName() + " is $" + taxBehavior.compute(value) + ".");
    }
}

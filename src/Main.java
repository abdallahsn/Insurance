import java.time.Year;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

    /*
    * SOFTWARE TESTING EXAM
        Analyze the specification below regarding an insurance contract:

        e Identify valid equivalence classes
        e Identify invalid equivalence classes
        e Write as you can the test cases in an online google sheet document with the
        essential statistics.
        --------------------------------------------------
        The basic insurance premium is determined by the Horse Power (HP) of the car:
        Under 50 HP - the basic premium is £400
        Up to 100 HP - the basic premium is £600
        The premium increases by £3.80 for every 1 HP after that
        For sports cars newer than 4 years, the basic premium is increased by a
        further 8% although this is reduced to 4% for older ones (sports cars).
        --------------------------------------------------
        If the car is parked in the street overnight an additional 8% is payable; however if
        the car is parked in a garage a 2% discount is allowed.
    */
    public static void main (String[] args) throws java.lang.Exception {
        while (true) {
            try {
                Scanner s = new Scanner(System.in);
                System.out.println("Enter the car's power in horsepower : ");
                double hp = s.nextDouble();
                System.out.println("Enter a year of manufacture : ");
                int year = s.nextInt();
                System.out.println("Is it a sports car (true/false) : ");
                boolean isSport = s.nextBoolean();
                System.out.println("Is there a night garage  (true/false) : ");
                boolean hasGarage = s.nextBoolean();
                if (validateInput(hp, year))
                    System.out.println(calculate(hp, year, isSport, hasGarage));
            }catch (InputMismatchException e){
                System.out.println("Input Mismatch Exception please input valid data \n\n\t\t\t---------------------\n\n");
            }
        }
    }

    public static boolean validateInput(double HP,int manufactureYear){
        if (HP<=0){
            System.out.println("Error please enter valid Horse Power");
            return false;
        }
        int year = Year.now().getValue();
        if (manufactureYear-1>year||manufactureYear<1900) {
            System.out.println("Error please enter valid manufacture year");
            return false;
        }
        return true;
    }
    public static double calculate(double HP,int manufactureYear,boolean isSport,boolean hasGarage){
        double finalInsuranceContract = 0;
        finalInsuranceContract = calculateBasicInsurancePremium(HP);
        if (isSport){
            finalInsuranceContract =  calculateBasicPremiumForSportsCars(finalInsuranceContract,manufactureYear);
        }
        finalInsuranceContract =calculateAdditionalCost(finalInsuranceContract,hasGarage);
        return finalInsuranceContract;
    }
    public static double calculateBasicInsurancePremium(double HP ){
        if (HP>0&&HP<50) {
            return 400;
        }
        else if (HP>=50&&HP<100) {
            return 600;
        }else {
            return 600+((HP-100)*3.80);
        }
    }
    public static double calculateBasicPremiumForSportsCars(double basicPremium,int manufactureYear){
        int year = Year.now().getValue();
        if (manufactureYear>= year-4){
            return basicPremium+(basicPremium*0.08);
        }else {
            return basicPremium+(basicPremium*0.04);
        }
    }
    public static double calculateAdditionalCost(double total,boolean hasGarage){
        if (hasGarage)
        {
            return total+(total*-0.02);
        }else {
            return total+(total*0.08);
        }
    }
    public static double calculateBasicPremiumForSportsCarsWithAdditional(double basicPremium,int manufactureYear,boolean hasGarage){
        int year = Year.now().getValue();
        if (manufactureYear>= year-4){
            if (hasGarage) {
                return basicPremium + (basicPremium * 0.06);
            }else {
                return basicPremium + (basicPremium * 0.16);
            }
        }else {
            if (hasGarage) {

                return basicPremium + (basicPremium * 0.02);
            }else {
                return basicPremium + (basicPremium * 0.12);

            }
        }
    }


}
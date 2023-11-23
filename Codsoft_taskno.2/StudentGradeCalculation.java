//Program to calculate the grade of a student
import java.util.*;
public class StudentGradeCalculation {
    public static void main(String args[]){
        GradeCalculation student = new GradeCalculation();
        student.takeMarks();
        student.getPercentage();
        student.gradeCal();
        student.displayResult();
    }
}
class GradeCalculation{
    int marks[] = new int[5];
    int totMarks;
    float percent;
    String grade;
    public void takeMarks(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the marks of 5 subject out of 100");
        for(int i = 0;i<5;i++){
            marks[i] = input.nextInt();
        }
    } 
    public void getPercentage(){
        this.totMarks = 0;
        for(int i = 0;i<5;i++){
            totMarks = totMarks+marks[i];
        }
        this.percent = (float) (totMarks/5);
    }
    public void gradeCal(){
        if(this.percent>=80){
            this.grade = "A";
        }
        else if(this.percent>=70){
            this.grade = "B"; 
        }
        else if(this.percent>=60){
            this.grade = "C";
        }
        else if(this.percent >= 33){
            this.grade = "D";
        }
        else{
            this.grade = "Fail";
        }
    }
    public void displayResult(){
        System.out.println("----------Student Result-----------");
        System.out.println("Total Marks = "+this.totMarks);
        System.out.println("Average Percentage = "+this.percent);
        System.out.println("Grade = "+this.grade);
    }
}

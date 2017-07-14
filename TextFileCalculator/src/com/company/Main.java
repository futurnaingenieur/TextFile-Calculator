package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\Mehdimess404\\IdeaProjects\\InterviewBeekeeper\\calculus.txt";

        FileParser fileParser = new FileParser(filePath);
        List<String> lines = fileParser.getStrings();

        System.out.println(computeCalculus(lines));
    }

    /*
     *   Recursive mehtod in charge of computing the result of an instruction list ( a simple list of Strings )
     */
    private static float computeCalculus(List<String> lines){

        float result;
        String operator;
        int nextIndex;
        int closingIndex=0;

        //This is the trivial case of the recursion, we just return the number
        if(lines.size()==1){
            return Float.parseFloat(lines.get(0));
        }
        //If the first line is a opening parenthesis, we have to get the all body of it until the appropriate closing one.
        if(lines.get(0).equals("(")){
            int count = 1;
            int i = 0;

            while(count>0){
                i++;
                if(lines.get(i).contains("(")){
                    count++;
                }
                else if(lines.get(i).contains(")")){
                    count--;
                    if(count==0){
                        closingIndex = i;
                    }
                }
            }
            // The result of parenthesis content will be recursively retrieve sending the inside lines as parameters.
            result =  computeCalculus(lines.subList(1,closingIndex));
        }
        else{
            //No parenthesis found, the first line is the number
            result = Float.parseFloat(lines.get(0));
            closingIndex = 0;
        }

        // Here, we need to check if we are at the end of a calculus or if their is still operators to handle
        if(lines.size()>closingIndex+2 && !lines.get(closingIndex+1).contains(")")){
            operator = lines.get(closingIndex+1);
            nextIndex = closingIndex+2;
        }
        else{
            // No more operators, we have our result
            return result;
        }
        float nbrIteration;
        /*
         *  This switch define how to handle operators.
         *  We could add any kind of operator if we wanted in order to extend the calculator features.
         */
        switch(operator){
            case "add":
                result += computeCalculus(lines.subList(nextIndex, lines.size()));
                break;
            case "sub":
                result -= computeCalculus(lines.subList(nextIndex, lines.size()));
                break;
            case "mul":
                result = result * computeCalculus(lines.subList(nextIndex, lines.size()));
                break;
            case "div":
                result = result / computeCalculus(lines.subList(nextIndex, lines.size()));
                break;
            // This operatore is the power operator (Ex: 2pow4 = 2*2*2*2 = 16
            case "pow":
                nbrIteration = computeCalculus(lines.subList(nextIndex, lines.size()));
                for(int i=1; i<nbrIteration; i++){
                    result = result * result;
                }
                break;
            // This operatore is the Nth Root of the number. (Ex: 16root4 = 2)
            case "root":
                nbrIteration = computeCalculus(lines.subList(nextIndex, lines.size()));
                result = (float) Math.pow(result, 1f/(nbrIteration));
                break;
        }
        return result;
    }
}

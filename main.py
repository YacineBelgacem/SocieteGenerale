import requests
import json

def animate(speed, init):

    #Test if the constraints on speed and len(init)
    # are respected
    if 1 <= speed <= 10:
        if 1 < len(init) <= 50:

            #Creation of two arrays to store RParticules and
            #LParticles in Rchamber and Lchamber.
            Rchamber = []
            Lchamber = []
            result = ""

            #Initial state :
            # - Parsing of init
            # - Filling of Lchamber and Rchamber
            for c in init:
                if c == ".":
                    Rchamber.append(".")
                    Lchamber.append(".")
                    result += "."
                elif c == "R":
                    Rchamber.append("R")
                    Lchamber.append(".")
                    result += "X"
                elif c == "L":
                    Rchamber.append(".")
                    Lchamber.append("L")
                    result += "X"
                else :
                    print("Invalid initialization : initial string can only contain 'R', 'L' and '.'")
                    return None

            #Show user initial state
            print(result)
            #Start loop where particles move
            while not Rchamber == Lchamber:
                result = ""

                #Parsing Lchamber from start to end and
                #Rchamber from end to beggining in order
                #to not override any value
                for i in range(len(init)):
                    if Lchamber[i] == "L":
                        Lchamber[i]="."
                        if i - speed >= 0:
                            Lchamber[i - speed] = "L"
                    if Rchamber[-i-1] == "R":
                        Rchamber[-i-1] = "."
                        if -i-1 + speed < 0:
                            Rchamber[-i -1 + speed] = "R"

                #Model intermediate result according to
                #the required format
                for i in range(len(init)):
                    if Lchamber[i] != "." or Rchamber[i] != ".":
                        result += "X"
                    else :
                        result += "."
                #Add intermediate state to the result
                print (result)
        else :
            print ("Invalid initialization : 1 <= length(initialization) <= 50 ")
    else :
        print (speed ,"Invalid speed input : 1 <= speed <= 10")


#Initialization of a looping program the user
#can decide to stop when the question is asked
end=""
while(end!="N"):
    try :
        #importation of the json format document uploaded
        #on github at the following URL.
        page = requests.get("https://raw.githubusercontent.com/YacineBelgacem/json_files/main/sg.json")
        data = page.json()
        print("Page succesfully loaded :)\n")
    except Exception as error_inst:
        print("Page failed to open :(\n", error_inst)

    #Show user the differents scenarios imported, requiring
    #Indexing them with an iterator
    i=0
    for item in data['items'] :
        print("Particle Chamber ",i,":\nParticles speed = " ,item['speed'], "\nInitial distribution :", item['init'], "\n")
        i+=1

    #Input required to choose the targeted scenario
    user = int(input("Please enter the index of the chamber you want to study ... "))
    animate(data['items'][user]['speed'], data['items'][user]['init'])
    #Input to continue or finish the program
    end = input("Do you want to study an other particle chamber ? Y/N...")


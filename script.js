var cat ={
    name : "kari kitiya",
    age :4,
    breed : "awajathaka",
    meow : function() {
        alert ("huttigo puthooooooo "); 
    },
    owner : {
        name : "ponna umesh",
        state : "udubaddawa"
    }
}

//get the cats name
 var catname = cat.name;


 //owners name

 var catowner = cat.owner.name;

 alert(catowner + " has a cute cat named " +catname)
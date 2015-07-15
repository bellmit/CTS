function disableFormElements()
{
   var allElements = document.body.getElementsByTagName("*");
   var arrayLength = allElements.length;
   for (i = 0; i < arrayLength; i++)
   {
      if (allElements[i].tagName == "INPUT" || allElements[i].tagName == "SELECT" || allElements[i].tagName == "TEXTAREA")
      {
         if (allElements[i].id != "ppwPrintButton" && allElements[i].id != "ppwCancelButton")
         {
            allElements[i].disabled = true;
         }
      }
   }
   
}
function cssChanger(styleSheetTitle)
{
   if (document.styleSheets)
   {
      var ssl = document.styleSheets.length;
      for (var i = 0; i < ssl; i++)
      {
         if (document.styleSheets[i].title != styleSheetTitle)
         {
            document.styleSheets[i].disabled = true;
         }
         else
         {
            document.styleSheets[i].disabled = false;
         }
      }
   }
}
      
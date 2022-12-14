
class Archive(val nameArh:String, val content:ArrayList<Note>): Common(name=nameArh){

    fun showNotesList(){
        val menu = Menu("Список заметок архива: '$nameArh'","0. Создать заметку", content)
        menu.showMenu()
    }

    fun waitingAction(){
        while (true){
            val number = askAction()
            val num = number.toIntOrNull()

            if(num !=null && num<=content.size+1){
                if(num == content.size+1)
                    break //printHeader("Завершение работы c заметками.",false)

                if(num == 0)
                {
                    printHeader("Создание новой заметки.",true)
                    if(createNote())
                        printHeader("Новая заметка создана.",false)
                    showNotesList()
                }
                else
                {
                    printHeader("Просмотр содержимого заметки.",true)
                    content.get(num-1).showData()
                    showNotesList()
                }
            }
            else {
                printHeader("Введено некорретное число: $number",false)
                showNotesList()
            }
        }
    }

    fun createNote(): Boolean{
        val nameNote = askActionWithExit( "Введите наименование заметки")
        if(!nameNote.equals("q")) {
            val contentNote = askActionWithExit( "Введите текст заметки")
            if(!contentNote.equals("q"))
                return content.add(Note(nameNote,contentNote,this))
            else
                cancelCreateNote()
        }
        else {
            cancelCreateNote()
        }
        return false
    }

    private fun cancelCreateNote(){
        printHeader("Создание заметки отменено.",false)
    }
}
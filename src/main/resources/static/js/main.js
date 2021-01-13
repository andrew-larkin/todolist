
$(function(){
var taskIdd;
    const appendTask = function(data){

        var taskCode = '<input type="checkbox" name="nameOfTask"/><a href="#" class="task-link" data-id="' +
            data.id + '">' + data.name + '</a><br>' +
            '<p class="task-datetime" data-id="' + data.id + '">' + data.date +
            '    ' + '</p>';
        $('#task-list')
            .append('<div class="task-quad" data-id="' + data.id + '">' + taskCode + '</div>');
    };

    const changeTask = function(data){ //изменение данных в блоке с задачей
        $("a", "div.task-quad[data-id='"+ data.id + "']").text(data.name);
        $("p", "div.task-quad[data-id='"+ data.id + "']").text(data.date);
        $.get('/tasks/', function(response)
                {
                   for(i in response) {
                        appendTask(response[i]);
                    }
                });
    };

    //загрузка задач из базы данных
    $.get('/tasks/', function(response)
        {
           for(i in response) {
                appendTask(response[i]);
            }
        });

 $('.task-link').click(function(){
        $('#task-form-update-delete').css('display', 'flex');
    });

    //Closing adding task form
    $('#task-form-update-delete').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Show adding task form
    $('#show-add-task-form').click(function(){
        $('#task-form').css('display', 'flex');
    });

    //Closing adding task form
    $('#task-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting task
    $(document).on('click', '.task-link', function(){
        var link = $(this);
        var taskId = link.data('id');
        taskIdd = taskId;
        $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function(response)
            {
                $('#task-form-update-delete').css('display', 'flex');

                var form = document.forms.updatee;
                var taskName = form.elements.name; //указание на поле с названием задачи
                var taskDate = Date(form.elements.date.value); //указание на поле с датой
                var taskDescription = form.elements.description;

                taskName.value = response.name;
                taskDate = new Date(response.date);
                taskDescription.value = response.description;
                console.log(taskDate);
                console.log(response.date);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Задание не найдено!');
                }
            }
        });
        return false;
    });

    //Update task
    $('#update-task').click(function() {
    var taskId = taskIdd;
    var data = $('#task-form-update-delete form').serializeArray();
    $.each(data,function(){
      console.log(this.name+'='+this.value);
    });
    $.ajax({
        method: "PUT",
        url: '/tasks/' + taskId,
        data: data,
        success: function(response)
        {
        $('#task-form-update-delete').css('display', 'none');
             changeTask(data);
                             var task = {};
                             task.id = response;
                             var dataArray = $('#task-form-update-delete form').serializeArray();
                             for(i in dataArray) {
                                 task[dataArray[i]['name']] = dataArray[i]['value'];
                             }
        }
    });
    return false;
    });

    //Delete task
   $('#delete-button').click(function() {
    $.ajax({
        type: "DELETE",
        url: '/tasks/' + taskIdd,
        success: function(taskIdd) {
            $('#task-form-update-delete').css('display', 'none'); //скрываем форму
            $("div.task-quad[data-id='" + taskIdd + "']").remove(); //удаляем div с задачей
    $.get('/tasks/', function(response)
        {
           for(i in response) {
                appendTask(response[i]);
            }
        });
        }
    });
    return false;
    });

    //Adding task
    $('#save-task').click(function()
    {
        var data = $('#task-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: data,
            success: function(response)
            {
                $('#task-form').css('display', 'none');
                var task = {};
                task.id = response;
                var dataArray = $('#task-form form').serializeArray();
                for(i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTask(task);
            }
        });
        return false;
    });
});
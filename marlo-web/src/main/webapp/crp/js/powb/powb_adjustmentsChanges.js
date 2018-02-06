$(document).ready(function() {
    /**
     * File upload (blueimp-tmpl)
     */

    var $uploadBlock = $('.fileUploadContainer');
    var $fileUpload = $uploadBlock.find('.upload');
    $fileUpload.fileupload({
        dataType: 'json',
        start: function(e) {
          var $ub = $(e.target).parents('.fileUploadContainer');
          $ub.addClass('blockLoading');
        },
        stop: function(e) {
          var $ub = $(e.target).parents('.fileUploadContainer');
          $ub.removeClass('blockLoading');
        },
        done: function(e,data) {
          var r = data.result;
          if(r.saved) {
            var $ub = $(e.target).parents('.fileUploadContainer');
            $ub.find('.contentResult').val(r.fileFileName);
            // Set file ID
            $ub.find('input.fileID').val(r.fileID);
            // Set file URL
            $ub.find('.fileUploaded a').attr('href', r.path + '/' + r.fileFileName)
          }
        },
        progressall: function(e,data) {
          var progress = parseInt(data.loaded / data.total * 100, 10);
        }
    });

    // Prepare data
    $fileUpload.bind('fileuploadsubmit', function(e,data) {

    });

    // Remove file event
    $uploadBlock.find('.removeIcon').on('click', function() {
      var $ub = $(this).parents('.fileUploadContainer');
      $ub.find('.contentResult').html("");
      $ub.find('input.fileID').val('');
      $ub.find('input.outcomeID').val('');
      // Clear URL
      $ub.find('.fileUploaded a').attr('href', '');
    });

    /** End File upload* */
});

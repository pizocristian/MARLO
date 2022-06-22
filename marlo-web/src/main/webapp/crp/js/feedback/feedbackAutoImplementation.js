var textareaComment, parentID, projectID, phaseID, userID, userCanManageFeedback, userCanLeaveComments, isFeedbackActive, textareaReply, newData;
var sectionName = $('#sectionNameToFeedback').val();
var contributionCRPAjaxURL = `/fieldsBySectionAndParent.do?sectionName=${sectionName}`;
var arrayName = 'fieldsMap';
let fieldID = '';
let qaComments = '';
fieldsSections = [];

function feedbackAutoImplementation (){
  console.log('%c feedback auto implementation init', 'background: white; color: black');
  parentID = $('#parentID').html();
  projectID = $('#projectID').html();
  phaseID = $('#phaseID').html();
  userID = $('#userID').html();
  userCanManageFeedback = $('#userCanManageFeedback').html();
  userCanLeaveComments = $('#userCanLeaveComments').html();
  console.log('can comment', userCanLeaveComments, 'can reply', userCanManageFeedback);
  isFeedbackActive = $('#isFeedbackActive').html();
  attachEventsFeedback();
}

function attachEventsFeedback() {

    console.log(sectionName)
    
  if (isFeedbackActive == 'true') {
    getQAComments();
    loadQACommentsIcons(contributionCRPAjaxURL, arrayName);
  }

  // Multiple comments-replies
  $('img.qaComment').on('click', function (event) {
    console.log("event");
    let name = this.name;
    console.log(name)
    let popUpTitle = $(this).attr('description');
    let qaPopup = $(`div[id^="qaPopup-${name}"]`);
    let block = $(`div[id^="qaCommentReply-${name}"]`);

    fieldID = $(this).attr('fieldID');

    block.each((index, item) => {
      if ($(item).attr('index') == 0) {
        $(item).find('textarea[id="New comment"]').prev('label').html(`Comment on "${popUpTitle}":`);
      }

      $(item).find('.sendCommentContainer').attr('name', `${name}[${index}]`);
      $(item).find('.agreeCommentBtn').attr('name', `${name}[${index}]`);
      $(item).find('.disagreeCommentBtn').attr('name', `${name}[${index}]`);
      $(item).find('.clarificationCommentBtn').attr('name', `${name}[${index}]`);
      $(item).find('.replyCommentBtn').attr('name', `${name}[${index}]`);
      $(item).find('.sendReplyContainer').attr('name', `${name}[${index}]`);
      $(item).find('div.addCommentContainer').attr('name', name);
    });

    loadCommentsByUser(name);

    if (event.pageX < 1000) {
      qaPopup.css('left', event.pageX);
    } else {
      qaPopup.css('left', 'min(100vw - 100px, 71vw)');
    }

    qaPopup.css('top', event.pageY);
    $('.qaPopup').hide().not(qaPopup);
    qaPopup.show();
  });

  $('div.closeComment').on('click', function () {
    let name = $(this).attr('name');
    let qaPopup = $(`div[id^="qaPopup-${name}"]`);
    qaPopup.hide();
  });

  $('div.sendCommentContainer').on('click', function () {
    let name = $(this).attr('name');
    let block = $(`div[id^="qaCommentReply-${name}"]`);
    let textarea = block.find('textarea[id="New comment"]');
    let value = textarea.val();
    let comment = textarea.next().html();
    let cleanComment;

    if (value && value != '') {
      cleanComment = value.replaceAll('.<br>.', '');
    } else {
      cleanComment = comment?.replaceAll('.<br>.', '');
    }
   
    cleanComment = cleanComment ? cleanComment.replaceAll('&nbsp;', ' ') : '';

    if (cleanComment != '' && cleanComment != ' ') {
      textarea.css('border', '1px solid #ccc');
      saveQAComment(cleanComment, fieldID, name, this);
    } else {
      textarea.css('border', '2px solid red');
    }
  });

  $('img.disagreeCommentBtn').on('click', function () {
    let name = $(this).attr('name');
    let commentID = $(this).attr('commentId');
    let block = $(this).parent().parent().parent();

    hideShowOptionButtons(block, '0');
    saveCommentStatus(0, commentID, name);
    block.find('img.replyCommentBtn').click();
  });

  $('img.agreeCommentBtn').on('click', function () {
    let name = $(this).attr('name');
    let commentID = $(this).attr('commentId');
    let block = $(this).parent().parent().parent();

    hideShowOptionButtons(block, '1');
    saveCommentStatus(1, commentID, name);
  });

  $('img.clarificationCommentBtn').on('click', function () {
    let name = $(this).attr('name');
    let commentID = $(this).attr('commentId');
    let block = $(this).parent().parent().parent();

    hideShowOptionButtons(block, '2');
    saveCommentStatus(2, commentID, name);
    block.find('img.replyCommentBtn').click();
  });

  $('img.replyCommentBtn').on('click', function () {
    let block = $(this).parent().parent().parent();

    block.find('.replyContainer').css('display', 'flex');
    block.find('.buttonsContainer').hide();
    block.find('.optionsContainer').hide();
  });

  $('div.sendReplyContainer').on('click', function () {
    let name = $(this).attr('name');
    let commentID = $(this).attr('commentId');
    let block = $(`div[id^="qaCommentReply-${name}"]`);
    let textarea = block.find('textarea[id="Reply"]');
    let value = textarea.val();
    let comment = textarea.next().html();
    let cleanComment;

    if (value && value != '') {
      cleanComment = value.replaceAll('.<br>.', '');
    } else {
      cleanComment = comment.replaceAll('.<br>.', '');
    }

    cleanComment = cleanComment.replaceAll('&nbsp;', ' ');

    if (cleanComment != '' && cleanComment != ' ') {
      textarea.css('border', '1px solid #ccc');
      saveFeedbackReply(cleanComment, commentID, name);
    } else {
      textarea.css('border', '2px solid red');
    }
  });

  $('div.addCommentContainer').on('click', function () {
    $(this).hide();
    let name = $(this).attr('name');
    let block = $(`div[id^="qaCommentReply-${name}"]`);
    block.find('.buttonsContainer').hide();
    let qaPopup = $(`div[id^="qaPopup-${name}"]`);
    let lastIndex = block.last().attr('index');
    lastIndex = parseInt(lastIndex) + 1;
    let commentReplyBlock = qaPopup.siblings('#qaTemplate').find('.qaPopup').children()[2];
    let newBlock = $(commentReplyBlock).clone(true).attr('id', `qaCommentReply-${name}[${lastIndex}]`);

    newBlock.attr('index', `${lastIndex}`);
    newBlock.find('.sendCommentContainer').attr('name', `${name}[${lastIndex}]`);
    newBlock.find('.sendReplyContainer').attr('name', `${name}[${lastIndex}]`);
    newBlock.find('.addCommentContainer').attr('name', `${name}`);
    newBlock.find('.addCommentContainer').attr('index', `${lastIndex}`);
    newBlock.appendTo(qaPopup).hide().show();
  });
}

function hideShowOptionButtons(block, status) {
    let textarea = block.find('textarea[id="Reply"]');
  
    switch (status) {
      case '0':
        textarea.prev().find('span.red.requiredTag').show();
        // block.find('.buttonsContainer').hide();
        block.find('img.disagreeCommentBtn').hide();
        block.find('.commentContainer').css('background', '#e8a9a4');
        block.find('.replyTextContainer').css('background', '#e8a9a4');
        block.find('img.agreeCommentBtn').hide();
        block.find('img.clarificationCommentBtn').hide();
        break;
      case '1':
        textarea.prev().find('span.red.requiredTag').hide();
        block.find('img.agreeCommentBtn').hide();
        block.find('.commentContainer').css('background', '#a8eaab');
        block.find('.replyTextContainer').css('background', '#a8eaab');
        block.find('img.disagreeCommentBtn').hide();
        block.find('img.clarificationCommentBtn').hide();
        break;
      case '2':
        textarea.prev().find('span.red.requiredTag').show();
        // block.find('.buttonsContainer').hide();
        block.find('img.clarificationCommentBtn').hide();
        block.find('.commentContainer').css('background', '#a4cde8');
        block.find('.replyTextContainer').css('background', '#a4cde8');
        block.find('img.agreeCommentBtn').hide();
        block.find('img.disagreeCommentBtn').hide();
        break;
      case '' || ' ':
        block.find('img.agreeCommentBtn').show();
        block.find('img.disagreeCommentBtn').show();
        block.find('img.clarificationCommentBtn').show();
        break;
      default:
        break;
    }
}
  
  // Multiple comments-replies
  function loadCommentsByUser(name) {
    // Removes the last index in brackets, i.e: [0]
    name = name.replace(/\[[^\]]*\]$/, '');
  
    if (qaComments.length > 0) {
      for (let i = 0; i < qaComments.length; i++) {
        if (qaComments[i].frontName == name) {
          let commentsLength = Object.keys(qaComments[i]).length;
  
          for (let j = 0; j < commentsLength; j++) {
            if (qaComments[i][j] !== undefined) {
              let block = $(`div[id^="qaCommentReply-${name}[${j}]"]`);
  
              if (j != 0) {
                block.find('textarea[id="New comment"]').prev().hide();
              }
  
              block.find('textarea[id="New comment"]').hide();
              block.find('textarea[id="New comment"]').next().next('p.charCount').hide();
              block.find('.commentContainer').show();
              block.find('.commentContainer .commentTitle').html(`Comment by ${qaComments[i][j].userName} at ${qaComments[i][j].date}`);
              block.find('.commentContainer p.commentReadonly').html(`${qaComments[i][j].comment}`);
              block.find('.sendCommentContainer').hide();
              block.find('.sendReplyContainer').attr('commentId', qaComments[i][j].commentId);
              block.find('.agreeCommentBtn').attr('commentId', qaComments[i][j].commentId);
              block.find('.disagreeCommentBtn').attr('commentId', qaComments[i][j].commentId);
              block.find('.clarificationCommentBtn').attr('commentId', qaComments[i][j].commentId);
              block.find('.replyCommentBtn').attr('commentId', qaComments[i][j].commentId);
  
              if (userCanLeaveComments == 'true') {
                let btnsContainer = block.find('.buttonsContainer');
                let addBtn = block.find('.addCommentContainer');
                const index = commentsLength - 2;
  
                if (addBtn.attr('index') == index) {
                  btnsContainer.show();
                  addBtn.show();
  
                  let blockDup = $(`div[id="qaCommentReply-${name}[${j + 1}]"]`);
  
                  if (blockDup.length != 0) {
                    btnsContainer.hide();
                    addBtn.hide();
                  }
                } else {
                  btnsContainer.hide();
                  addBtn.hide();
                }
              }
  
              if (userCanManageFeedback == 'true') {
                block.find('.buttonsContainer').show();
                block.find('.optionsContainer').css('display', 'flex');
              }
  
              hideShowOptionButtons(block, qaComments[i][j].status);
  
              let replyLength = Object.keys(qaComments[i][j].reply).length;
  
              if (replyLength !== 0) {
                block.find('textarea[id="Reply"]').parent().hide();
                block.find('.replyContainer').css('display', 'flex');
                block.find('.replyTextContainer').show();
                block.find('.replyTextContainer .replyTitle').html(`Reply by ${qaComments[i][j].reply['userName']} at ${qaComments[i][j].reply['date']}`);
                block.find('.replyTextContainer p.replyReadonly').html(`${qaComments[i][j].reply['text']}`);
                // block.find('.buttonsContainer').hide();
                block.find('.replyCommentBtn').hide();
                block.find('.sendReplyContainer').hide();
              } else {
                if (qaComments[i][j].status && qaComments[i][j].status != '') {
                  if (qaComments[i][j].status == '1') {
                    block.find('textarea[id="Reply"]').parent().show();
                    block.find('.replyContainer').hide();
                    block.find('.replyTextContainer').hide();
                    block.find('.replyCommentBtn').show();
                    block.find('.sendReplyContainer').show();
                  } else {
                    block.find('textarea[id="Reply"]').parent().show();
                    block.find('.replyContainer').css('display', 'flex');
                    block.find('.replyTextContainer').hide();
                    block.find('.replyCommentBtn').hide();
                    block.find('.sendReplyContainer').show();
                  }
                } else {
                  block.find('.replyCommentBtn').hide();
                }
              }
            }
          }
        }
      }
    }
  }

  runaddfeedbackFlexItemsClass = true;
  function addfeedbackFlexItemsClass(fieldsMap){
    if (!runaddfeedbackFlexItemsClass) return;
    fieldsMap.map(field=>{
      // $(`[name="${field.fieldName}"]`).closest('.fieldReference')
      $(`[name^="${field.fieldName}"]`).closest('.fieldReference').appendTo($(`[name^="${field.fieldName}"]`).closest('.fieldReference').prev());
      $(`[name^="${field.fieldName}"]`).closest('.fieldReference').closest('.feedback-flex-items').next().appendTo($(`[name^="${field.fieldName}"]`).closest('.fieldReference').closest('.feedback-flex-items'))
    })
    runaddfeedbackFlexItemsClass = false;
  }
  
  function loadQACommentsIcons(ajaxURL, arrayName) {
    $.ajax({
      url: baseURL + ajaxURL,
      async: false,
      success: function (data) {
        
        fieldsSections = data?.fieldsMap;
        addfeedbackFlexItemsClass(fieldsSections);
        console.log(fieldsSections)
        if ((userCanLeaveComments == 'true') || (userCanManageFeedback == 'true' && qaComments.length > 0)) {
          if (data && Object.keys(data).length != 0) {
            newData = data[arrayName].map(function (x) {
              var arr = [];
              arr.push(x.fieldID);
              arr.push(x.fieldName);
              arr.push(x.description);
              return arr;
            });
            showQAComments(newData);
          }
        }
      }
    });
  }
  
  function showQAComments(data) {
    console.log(data)
    data.map(function (x) {
      var commentIcon = $(`img.qaComment[name="${x[1]}"]`);
      commentIcon.attr('fieldID', `${x[0]}`);
      commentIcon.attr('description', `${x[2]}`);
      
      for (let i = 0; i < qaComments.length; i++) {
        
        if (x[1] == qaComments[i].frontName) {
          getNumberOfComments(x[1]);
          let commentsLength = Object.keys(qaComments[i]).length;
  
          for (let j = 0; j < commentsLength; j++) {
            if (qaComments[i][j] != undefined) {
              if (qaComments[i][j].comment != '') {
                commentIcon.attr('src', qaCommentsStatus('pending'));
  
                if (qaComments[i][j].status != '') {
                  if (qaComments[i][j].status == '1') {
                    commentIcon.attr('src', qaCommentsStatus('done'));
                  } else {
                    if (Object.keys(qaComments[i][j].reply).length != 0) {
                      commentIcon.attr('src', qaCommentsStatus('done'));
                    } else {
                      commentIcon.attr('src', qaCommentsStatus('pending'));
                    }
                  }
                } else {
                  commentIcon.attr('src', qaCommentsStatus('pending'));
                }
              }
            }
          }
          commentIcon.show();
          commentIcon.parent().css('display', 'flex');
        }
      }
  
      if (userCanLeaveComments == 'true') {
        commentIcon.show();
        commentIcon.parent().css('display', 'flex');
      }
    });
  }
  
  function qaCommentsStatus(status) {
    switch (status) {
      case 'start':
        return `${baseURL}/global/images/comment.png`;
      case 'pending':
        return `${baseURL}/global/images/comment_yellow.png`;
      case 'done':
        return `${baseURL}/global/images/comment_green.png`;
      default:
        break;
    }
  }
  
  // Multiple comments-replies
  function saveQAComment(comment, fieldID, name, reference) {
    let indexToCute = $(reference).attr("name").substring(0,$(reference).attr("name").length-3);
    let objectField = fieldsSections.find(field => field.fieldName == indexToCute)
    let inputValue = $(`input[name="${objectField.parentFieldDescription}"]`).val()
    var finalAjaxURL = `/saveFeedbackComments.do?sectionName=${sectionName}&parentID=${parentID}&comment=${comment}&phaseID=${phaseID}&fieldID=${fieldID}&userID=${userID}&projectID=${projectID}&parentFieldDescription=${inputValue}`;
  
    $.ajax({
      url: baseURL + finalAjaxURL,
      async: false,
      success: function (data) {
        getQAComments();
        loadCommentsByUser(name);
        loadQACommentsIcons(contributionCRPAjaxURL, arrayName);
      }
    });
  }
  
  function saveFeedbackReply(reply, commentID, name) {
    var finalAjaxURL = `/saveFeedbackReply.do?reply=${reply}&commentID=${commentID}&userID=${userID}`;
  
    $.ajax({
      url: baseURL + finalAjaxURL,
      async: false,
      success: function (data) {
        getQAComments();
        loadCommentsByUser(name);
        loadQACommentsIcons(contributionCRPAjaxURL, arrayName);
      }
    });
  }
  
  function saveCommentStatus(status, commentID, name) {
    var finalAjaxURL = `/saveCommentStatus.do?status=${status}&commentID=${commentID}&userID=${userID}`;
  
    $.ajax({
      url: baseURL + finalAjaxURL,
      async: false,
      success: function (data) {
        getQAComments();
        loadCommentsByUser(name);
        loadQACommentsIcons(contributionCRPAjaxURL, arrayName);
      }
    });
  }
  
  function getQAComments() {
    var finalAjaxURL = `/feedbackComments2.do?sectionName=${sectionName}&parentID=${parentID}&phaseID=${phaseID}`;
  
    $.ajax({
      url: baseURL + finalAjaxURL,
      async: false,
      success: function (data) {
        if (data && Object.keys(data).length != 0) {
          qaComments = data['comments'];
          console.log(qaComments)
        }
      }
    });
  }
  
  function deleteQAComment(commentID) {
    var finalAjaxURL = `/fdeleteComment.do?commentID=${commentID}`;
  
    $.ajax({
      url: baseURL + finalAjaxURL,
      async: false,
      success: function (data) {
  
      }
    });
  }
  
  function getNumberOfComments(name) {
    var finalAjaxURL = `/getCommentStatus.do?sectionName=${sectionName}&parentID=${parentID}&phaseID=${phaseID}&fieldDescription=${name}`;
  
    $.ajax({
      url: baseURL + finalAjaxURL,
      async: false,
      success: function (data) {
        if (data && Object.keys(data).length != 0) {
          newData = data['comments'].map(function (x) {
            var arr = [];
            arr.push(x.answeredComments);
            arr.push(x.totalComments);
            return arr;
          });
          loadNumberOfComments(name, newData);
        }
      }
    });
  }
  
  function loadNumberOfComments(name, data) {
    data.map(function (x) {
      let p = $(`img.qaComment[name="${name}"]`).prev().find('p');
      p.css('display', 'block');
      p.html(`${x[0]}/${x[1]}`);
    });
  }

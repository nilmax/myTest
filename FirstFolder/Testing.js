$(el).each(function(){
                var hasChildren = this.children.length > 0;

                html += '<li class="' + (hasChildren ? 'closed' : '') + '">';

                var iconClass = hasChildren ? "icon-folder-close" : "icon-file";
                var spanClass = hasChildren ? 'folder' : 'file';

                var text = this.item.path.split("/").pop();
                var value = this.item.path == "root" ? "" : this.item.path;

                html += '<div><input type="radio" name="divFileBrowser" value="' + value + '"></input><span class="' + spanClass + '" onclick="showFileContent(\'' + this.item.url + '\')"><i class="' + iconClass + '"></i>&nbsp;' + text + '</span></div>';

                if (hasChildren) {
                    html += createBranch(this.children);
                }

                html += '</li>';
            });

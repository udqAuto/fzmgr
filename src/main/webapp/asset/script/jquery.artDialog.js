/*
* artDialog 4.1.0
* Date: 2011-08-06 22:10
* http://code.google.com/p/artdialog/
* (c) 2009-2010 TangBin, http://www.planeArt.cn
*
* This is licensed under the GNU LGPL, version 2.1 or later.
* For details, see: http://creativecommons.org/licenses/LGPL/2.1/
*/

; (function ($, window, undefined) {

    var _box, _thisScript, _skin, _path,
	_count = 0,
	_$window = $(window),
	_$document = $(document),
	_$html = $('html'),
	_$body = $(function () { _$body = $('body') }),
	_elem = document.documentElement,
	_isIE6 = ! -[1, ] && !('minWidth' in _elem.style),
	_isMobile = 'createTouch' in document && !('onmousemove' in _elem)
		|| /(iPhone|iPad|iPod)/i.test(navigator.userAgent),
	_eventDown = _isMobile ? 'touchstart' : 'mousedown',
	_expando = 'artDialog' + (new Date).getTime();

    var artDialog = function (config, yesFn, noFn) {
        config = config || {};
        if (typeof config === 'string' || config.nodeType === 1) {
            config = { content: config, fixed: !_isMobile };
        };

        var api, buttons = [],
		defaults = artDialog.defaults,
		elem = config.follow = this.nodeType === 1 && this || config.follow;

        // �ϲ�Ĭ������
        for (var i in defaults) {
            if (config[i] === undefined) config[i] = defaults[i];
        };

        // ���ظ���ģʽ���ظ������ID
        if (typeof elem === 'string') elem = $(elem)[0];
        config.id = elem && elem[_expando + 'follow'] || config.id || _expando + _count;
        api = artDialog.list[config.id];
        if (elem && api) return api.follow(elem).zIndex().focus();
        if (api) return api.zIndex();

        // Ŀǰ�����ƶ��豸��fixed֧�ֲ���
        if (_isMobile) config.fixed = false;

        // ��ť����
        if (!$.isArray(config.button)) {
            config.button = config.button ? [config.button] : [];
        };
        yesFn = yesFn || config.yesFn;
        noFn = noFn || config.noFn;
        yesFn && config.button.push({
            name: config.yesText,
            callback: yesFn,
            focus: true
        });
        noFn && config.button.push({
            name: config.noText,
            callback: noFn
        });

        // zIndexȫ������
        artDialog.defaults.zIndex = config.zIndex;

        _count++;
        return artDialog.list[config.id] = _box ?
		_box._init(config) : new artDialog.fn._init(config);
    };

    artDialog.fn = artDialog.prototype = {

        version: '4.1.0',

        _init: function (config) {
            var that = this, DOM,
			icon = config.icon,
			iconBg = icon && (_isIE6 ? { png: 'icons/' + icon + '.png' }           
			: { backgroundImage: 'url(\'' + config.path + '/skins/icons/' + icon + '.png\')' });   
            that.config = config;
            that._listeners = {};
            that._fixed = _isIE6 ? false : config.fixed;
            that._elemBack = that._timer = that._focus = that._isClose = that._lock = null;

            DOM = that.DOM = that.DOM || that._getDOM();
            DOM.wrap.addClass(config.skin);
            DOM.close[config.noFn === false ? 'hide' : 'show']();
            DOM.icon[0].style.display = icon ? '' : 'none';
            DOM.iconBg.css(iconBg || { background: 'none' });
            DOM.se.css('cursor', config.resize ? 'se-resize' : 'auto');
            DOM.title.css('cursor', config.drag ? 'move' : 'auto');
            DOM.content.css('padding', config.padding);

            that[config.show ? 'show' : 'hide'](true)
		.button(config.button)
		.title(config.title)
		.content(config.content)
		.size(config.width, config.height)
		.zIndex(config.zIndex)
		.time(config.time);

            config.follow
		? that.follow(config.follow)
		: that.position(config.left, config.top);

            config.lock && that.lock();
            config.focus && that.focus();

            that._addEvent();
            _isIE6 && that._pngFix();
            _box = null;

            config.initFn && config.initFn.call(that, window);
            return that;
        },

        /**
        * ��������
        * @param	{String, HTMLElement}	���� (��ѡ)
        * @return	{this, HTMLElement}				����޲����򷵻���������DOM����
        */
        content: function (msg) {
            var prev, next, parent, display,
			that = this,
			content = that.DOM.content,
			elem = content[0];

            that._elemBack = null;

            if (msg === undefined) {
                return elem;
            } else if (typeof msg === 'string') {
                content.html(msg);
            } else if (msg && msg.nodeType === 1) {

                // �ô����Ԫ���ڶԻ���رպ���Է��ص�ԭ���ĵط�
                display = msg.style.display;
                prev = msg.previousSibling;
                next = msg.nextSibling;
                parent = msg.parentNode;
                that._elemBack = function () {
                    if (prev && prev.parentNode) {
                        prev.parentNode.insertBefore(msg, prev.nextSibling);
                    } else if (next && prev.parentNode) {
                        next.parentNode.insertBefore(msg, next);
                    } else if (parent) {
                        parent.appendChild(msg);
                    };
                    msg.style.display = display;
                };

                content.html('');
                elem.appendChild(msg);
                msg.style.display = 'block';

            };

            _isIE6 && that._selectFix();
            that._runScript(elem);

            return that;
        },

        /**
        * ���ñ���
        * @param	{String, Boolean}	��������. Ϊfalse�����ر�����
        * @return	{this, HTMLElement}	����޲����򷵻�������DOM����
        */
        title: function (text) {
            var DOM = this.DOM,
			wrap = DOM.wrap,
			title = DOM.title,
			className = 'aui_state_noTitle';

            if (text === undefined) {
                return title[0];
            };

            if (text === false) {
                title.hide().html('');
                wrap.addClass(className);
            } else {
                title.show().html(text);
                wrap.removeClass(className);
            };

            return this;
        },

        /**
        * λ��
        * @param	{Number, String}
        * @param	{Number, String}
        */
        position: function (left, top) {
            var that = this,
			config = that.config,
			wrap = that.DOM.wrap,
			ie6Fixed = _isIE6 && that.config.fixed,
			docLeft = _$document.scrollLeft(),
			docTop = _$document.scrollTop(),
			dl = that._fixed ? 0 : docLeft,
			dt = that._fixed ? 0 : docTop,
			ww = _$window.width(),
			wh = _$window.height(),
			ow = wrap[0].offsetWidth,
			oh = wrap[0].offsetHeight,
			style = wrap[0].style;

            if (!left && left !== 0) left = config.left;
            if (!top && top !== 0) top = config.top;
            config.left = left;
            config.top = top;

            left = that._toNumber(left, ww - ow);
            if (typeof left === 'number') left = ie6Fixed ? (left += docLeft) : left + dl;

            if (top === 'goldenRatio') {
                // �ƽ������ֱ����
                top = (oh < 4 * wh / 7 ? wh * 0.382 - oh / 2 : (wh - oh) / 2) + dt;
            } else {
                top = that._toNumber(top, wh - oh);
                if (typeof top === 'number') top = ie6Fixed ? (top += docTop) : top + dt;
            };

            if (typeof left === 'number') {
                style.left = Math.max(left, dl) + 'px';
            } else if (typeof left === 'string') {
                style.left = left;
            };

            if (typeof top === 'number') {
                style.top = Math.max(top, dt) + 'px';
            } else if (typeof top === 'string') {
                style.top = top;
            };

            /*that.config.follow = null;*/
            that._autoPositionType();

            return that;
        },

        /**
        *	�ߴ�
        *	@param	{Number, String}	���
        *	@param	{Number, String}	�߶�
        *	@return	this
        */
        size: function (width, height) {
            var maxWidth, maxHeight, scaleWidth, scaleHeight,
			that = this,
			config = that.config,
			DOM = that.DOM,
			wrap = DOM.wrap,
			main = DOM.main,
			wrapStyle = wrap[0].style,
			style = main[0].style;

            if (!width && width !== 0) width = config.width;
            if (!height && height !== 0) height = config.height;

            maxWidth = _$window.width() - wrap[0].offsetWidth + main[0].offsetWidth;
            scaleWidth = that._toNumber(width, maxWidth);
            config.width = width;
            width = scaleWidth;

            maxHeight = _$window.height() - wrap[0].offsetHeight + main[0].offsetHeight;
            scaleHeight = that._toNumber(height, maxHeight);
            config.height = height;
            height = scaleHeight;

            if (typeof width === 'number') {
                wrapStyle.width = 'auto';
                style.width = Math.max(that.config.minWidth, width) + 'px';
                wrapStyle.width = wrap[0].offsetWidth + 'px'; // ��ֹδ�����ȵı������������ұ߽߱�����
            } else if (typeof width === 'string') {
                style.width = width;
                width === 'auto' && wrap.css('width', 'auto');
            };

            if (typeof height === 'number') {
                style.height = Math.max(that.config.minHeight, height) + 'px';
            } else if (typeof height === 'string') {
                style.height = height;
            };

            _isIE6 && that._selectFix();

            return that;
        },

        /**
        * ����Ԫ��
        * @param	{HTMLElement}
        */
        follow: function (elem) {
            var $elem, that = this;

            if (typeof elem === 'string' || elem && elem.nodeType === 1) {
                $elem = $(elem);
            };
            if (!$elem || $elem.css('display') === 'none') {
                return that.position(that.config.left, that.config.top);
            };

            var winWidth = _$window.width(),
			winHeight = _$window.height(),
			docLeft = _$document.scrollLeft(),
			docTop = _$document.scrollTop(),
			offset = $elem.offset(),
			width = $elem[0].offsetWidth,
			height = $elem[0].offsetHeight,
			left = that._fixed ? offset.left - docLeft : offset.left,
			top = that._fixed ? offset.top - docTop : offset.top,
			wrap = that.DOM.wrap[0],
			style = wrap.style,
			wrapWidth = wrap.offsetWidth,
			wrapHeight = wrap.offsetHeight,
			setLeft = left - (wrapWidth - width) / 2,
			setTop = top + height,
			dl = that._fixed ? 0 : docLeft,
			dt = that._fixed ? 0 : docTop;

            setLeft = setLeft < dl ? left :
		(setLeft + wrapWidth > winWidth) && (left - wrapWidth > dl)
		? left - wrapWidth + width
		: setLeft;

            setTop = (setTop + wrapHeight > winHeight + dt)
		&& (top - wrapHeight > dt)
		? top - wrapHeight
		: setTop;

            style.left = setLeft + 'px';
            style.top = setTop + 'px';

            that.config.follow = elem;
            $elem[0][_expando + 'follow'] = that.config.id;
            that._autoPositionType();
            return that;
        },

        /**
        * �Զ��尴ť
        * @example
        button({
        name: 'login',
        callback: function () {},
        disabled: false,
        focus: true
        }, .., ..)
        */
        button: function () {
            var that = this,
			ags = arguments,
			DOM = that.DOM,
			wrap = DOM.wrap,
			buttons = DOM.buttons,
			elem = buttons[0],
			strongButton = 'aui_state_highlight',
			list = $.isArray(ags[0]) ? ags[0] : [].slice.call(ags);

            $.each(list, function (i, val) {
                var name = val.name,
				listeners = that._listeners,
				isNewButton = !listeners[name],
				button = !isNewButton ?
					listeners[name].elem :
					document.createElement('button');

                if (!listeners[name]) listeners[name] = {};
                if (val.callback) listeners[name].callback = val.callback;
                if (val.className) button.className = val.className;
                if (val.focus) {
                    that._focus && that._focus.removeClass(strongButton);
                    that._focus = $(button).addClass(strongButton);
                    that.focus();
                };

                button[_expando + 'callback'] = name;
                button.disabled = !!val.disabled;

                if (isNewButton) {
                    button.innerHTML = name;
                    listeners[name].elem = button;
                    elem.appendChild(button);
                };
            });

            buttons[0].style.display = list.length ? '' : 'none';

            _isIE6 && that._selectFix();
            return that;
        },

        /** ��ʾ�Ի��� */
        show: function (lock) {
            this.DOM.wrap.show();
            !lock && this._lockMaskWrap && this._lockMaskWrap.show();
            return this;
        },

        /** ���ضԻ��� */
        hide: function (lock) {
            this.DOM.wrap.hide();
            !lock && this._lockMaskWrap && this._lockMaskWrap.hide();
            return this;
        },

        /** �رնԻ��� */
        close: function () {
            var that = this,
			DOM = that.DOM,
			wrap = DOM.wrap,
			list = artDialog.list,
			fn = that.config.closeFn,
			follow = that.config.follow;

            if (that._isClose) return that;
            that.time();
            if (typeof fn === 'function' && fn.call(that, window) === false) {
                return that;
            };

            that.unlock();
            wrap[0].className = wrap[0].style.cssText = '';

            that._elemBack && that._elemBack();
            DOM.title.html('');
            DOM.content.html('');
            DOM.buttons.html('');

            if (artDialog.focus === that) artDialog.focus = null;
            if (follow) follow[_expando + 'follow'] = null;
            delete list[that.config.id];
            that._isClose = true;
            that._removeEvent();
            that.hide(true)._setAbsolute();

            _box ? wrap.remove() : _box = that;
            return that;
        },

        /**
        * ��ʱ�ر�
        * @param	{Number}	��λΪ��, �޲�����ֹͣ��ʱ��
        */
        time: function (second) {
            var that = this,
			cancel = that.config.noText,
			timer = that._timer;

            timer && clearTimeout(timer);

            if (second) {
                that._timer = setTimeout(function () {
                    that._trigger(cancel);
                }, 1000 * second);
            };

            return that;
        },

        /** ����ť���ӽ��� */
        focus: function () {
            var elemFocus, content,
			that = this,
			config = that.config,
			DOM = that.DOM;

            elemFocus = that._focus && that._focus[0] || DOM.close[0];

            try {
                elemFocus && elemFocus.focus();
            } catch (e) { };

            return that;
        },

        /** �ö�z-index */
        zIndex: function () {
            var that = this,
			wrap = that.DOM.wrap,
			index = artDialog.defaults.zIndex++,
			focusElem = artDialog.focus;

            wrap.css('zIndex', index);
            that._lockMask && that._lockMask.css('zIndex', index - 1);

            // ������߲����ʽ
            if (focusElem) focusElem.DOM.wrap.removeClass('aui_state_focus');
            artDialog.focus = that;
            wrap.addClass('aui_state_focus');

            return that;
        },

        /** �������� */
        lock: function () {
            if (this._lock) return this;

            var that = this,
			index = artDialog.defaults.zIndex += 2,
			wrap = that.DOM.wrap,
			config = that.config,
			docWidth = _$document.width(),
			docHeight = _$document.height(),
			lockMaskWrap = that._lockMaskWrap || $(_$body[0].appendChild(document.createElement('div'))),
			lockMask = that._lockMask || $(lockMaskWrap[0].appendChild(document.createElement('div'))),
			domTxt = '(document).documentElement',
			sizeCss = _isMobile ? 'width:' + docWidth + 'px;height:' + docHeight
				+ 'px' : 'width:100%;height:100%',
			ie6Css = _isIE6 ?
				'position:absolute;left:expression(' + domTxt + '.scrollLeft);top:expression('
				+ domTxt + '.scrollTop);width:expression(' + domTxt
				+ '.clientWidth);height:expression(' + domTxt + '.clientHeight)'
			: '';

            wrap.css('zIndex', index);

            lockMaskWrap[0].style.cssText = sizeCss + ';position:absolute;z-index:'
			+ (index - 1) + ';top:0;left:0;overflow:hidden;' + ie6Css;
            lockMask[0].style.cssText = 'height:100%;background:' + config.background
			+ ';filter:alpha(opacity=0);opacity:0';

            // ��IE6���������ܹ���ס�����ؼ�
            if (_isIE6) lockMask.html(
			'<iframe src="about:blank" style="width:100%;height:100%;position:absolute;' +
			'top:0;left:0;z-index:-1;filter:alpha(opacity=0)"></iframe>');

            lockMask.stop();
            lockMask[0].ondblclick = function () { that.close() };
            if (config.duration === 0) {
                lockMask.css({ opacity: config.opacity });
            } else {
                lockMask.animate({ opacity: config.opacity }, config.duration);
            };

            that._lockMaskWrap = lockMaskWrap;
            that._lockMask = lockMask;

            that._lock = true;
            return that;
        },

        /** �⿪���� */
        unlock: function (onfx) {
            var that = this,
			lockMaskWrap = that._lockMaskWrap,
			lockMask = that._lockMask;

            if (!that._lock) return that;
            var style = lockMaskWrap[0].style;
            var un = function () {
                if (_isIE6) {
                    style.removeExpression('width');
                    style.removeExpression('height');
                    style.removeExpression('left');
                    style.removeExpression('top');
                };
                style.cssText = 'display:none';

                if (_box) {
                    lockMaskWrap.remove();
                    that._lockMaskWrap = that._lockMask = null;
                };
            };

            lockMask.stop()
            lockMask[0].ondblclick = null;
            if (that.config.duration === 0) {// ȡ�����������ٹر�
                un();
            } else {
                lockMask.animate({ opacity: 0 }, that.config.duration, un);
            };

            that._lock = false;
            return that;
        },

        // ��ȡԪ��
        _getDOM: function (wrap) {
            wrap = document.createElement('div');
            wrap.style.cssText = 'position:absolute;left:0;top:0';
            wrap.innerHTML = artDialog.templates;
            document.body.appendChild(wrap);

            var DOM = { wrap: $(wrap) },
			els = wrap.getElementsByTagName('*'),
			elsLen = els.length;

            for (var i = 0; i < elsLen; i++) {
                DOM[els[i].className.split('aui_')[1]] = $(els[i]);
            };

            return DOM;
        },

        // px��%��λת������ֵ (�ٷֱȵ�λ�������ֵ����)
        // �����ĵ�λ����ԭֵ
        _toNumber: function (thisValue, maxValue) {
            if (!thisValue && thisValue !== 0 || typeof thisValue === 'number') {
                return thisValue;
            };

            var last = thisValue.length - 1;
            if (thisValue.lastIndexOf('px') === last) {
                thisValue = parseInt(thisValue);
            } else if (thisValue.lastIndexOf('%') === last) {
                thisValue = parseInt(maxValue * thisValue.split('%')[0] / 100);
            };

            return thisValue;
        },

        // ��IE6 CSS֧��PNG����
        _pngFix: function () {
            var i = 0, elem, png, pngPath, runtimeStyle,
			path = artDialog.defaults.path + '/skins/',
			list = this.DOM.wrap[0].getElementsByTagName('*');

            for (; i < list.length; i++) {
                elem = list[i];
                png = elem.currentStyle['png'];
                if (png) {
                    pngPath = path + png;
                    runtimeStyle = elem.runtimeStyle;
                    runtimeStyle.backgroundImage = 'none';
                    runtimeStyle.filter = "progid:DXImageTransform.Microsoft." +
					"AlphaImageLoader(src='" + pngPath + "',sizingMethod='crop')";
                };
            };
        },

        // ǿ�Ƹ���IE6�����ؼ�
        _selectFix: function () {
            var elem = this.DOM.wrap[0],
			expando = _expando + 'iframeMask',
			iframe = elem[expando],
			width = elem.offsetWidth,
			height = elem.offsetHeight,
			left = -(width - elem.clientWidth) / 2 + 'px',
			top = -(height - elem.clientHeight) / 2 + 'px';

            width = width + 'px';
            height = height + 'px';

            if (iframe) {
                iframe.style.width = width;
                iframe.style.height = height;
            } else {
                iframe = elem.appendChild(document.createElement('iframe'));
                elem[expando] = iframe;
                iframe.src = 'about:blank';
                iframe.style.cssText = 'position:absolute;z-index:-1;left:'
				+ left + ';top:' + top
				+ ';width:' + width + ';height:' + height
				+ ';filter:alpha(opacity=0)';
            };
        },

        // ����HTMLƬ�����Զ������ͽű�����thisָ��artDialog�ڲ�
        // <script type="text/dialog">/* [code] */</script>
        _runScript: function (elem) {
            var fun, i = 0, n = 0,
			tags = elem.getElementsByTagName('script'),
			length = tags.length,
			script = [];

            for (; i < length; i++) {
                if (tags[i].type === 'text/dialog') {
                    script[n] = tags[i].innerHTML;
                    n++;
                };
            };

            if (script.length) {
                script = script.join('');
                fun = new Function(script);
                fun.call(this);
            };
        },

        // �Զ��л���λ����
        _autoPositionType: function () {
            var that = this;
            that[that.config.fixed ? '_setFixed' : '_setAbsolute']();
        },


        // ���þ�ֹ��λ
        // IE6 Fixed @see: http://www.planeart.cn/?p=877
        _setFixed: (function () {
            _isIE6 && $(function () {
                var bg = 'backgroundAttachment';
                if (_$html.css(bg) !== 'fixed' && _$body.css(bg) !== 'fixed') {
                    _$html.css({
                        backgroundImage: 'url(about:blank)',
                        backgroundAttachment: 'fixed'
                    });
                };
            });

            return function () {
                var $elem = this.DOM.wrap,
				style = $elem[0].style;

                if (_isIE6) {
                    var left = parseInt($elem.css('left')),
					top = parseInt($elem.css('top')),
					sLeft = _$document.scrollLeft(),
					sTop = _$document.scrollTop(),
					txt = '(document.documentElement)';

                    this._setAbsolute();
                    style.setExpression('left', 'eval(' + txt + '.scrollLeft + '
					+ (left - sLeft) + ') + "px"');
                    style.setExpression('top', 'eval(' + txt + '.scrollTop + '
					+ (top - sTop) + ') + "px"');
                } else {
                    style.position = 'fixed';
                };
            };
        } ()),

        // ���þ��Զ�λ
        _setAbsolute: function () {
            var style = this.DOM.wrap[0].style;

            if (_isIE6) {
                style.removeExpression('left');
                style.removeExpression('top');
            };

            style.position = 'absolute';
        },

        // ��ť�¼�����
        _trigger: function (id) {
            var that = this,
			fn = that._listeners[id] && that._listeners[id].callback;
            return typeof fn !== 'function' || fn.call(that, window) !== false ?
			that.close() : that;
        },

        // �¼�����
        _addEvent: function () {
            var winResize, resizeTimer,
			that = this,
			config = that.config,
			DOM = that.DOM,
			winSize = _$window.width() * _$window.height();

            that._click = function (event) {
                var target = event.target, callbackID;

                if (target.disabled) return false; // IE BUG

                if (target === DOM.close[0]) {
                    that._trigger(config.noText);
                    return false;
                } else {
                    callbackID = target[_expando + 'callback'];
                    callbackID && that._trigger(callbackID);
                };
            };

            that._eventDown = function () {
                that.zIndex();
            };

            winResize = function () {
                var newSize,
				oldSize = winSize,
				elem = config.follow,
				width = config.width,
				height = config.height,
				left = config.left,
				top = config.top;

                if ('all' in document) {
                    // IE6~7 window.onresize bug
                    newSize = _$window.width() * _$window.height();
                    winSize = newSize;
                    if (oldSize === newSize) return;
                };

                if (width || height) that.size(width, height);

                if (elem) {
                    that.follow(elem);
                } else if (left || top) {
                    that.position(left, top);
                };
            };

            that._winResize = function () {
                resizeTimer && clearTimeout(resizeTimer);
                resizeTimer = setTimeout(function () {
                    winResize();
                }, 40);
            };

            // �������
            DOM.wrap.bind('click', that._click)
		.bind(_eventDown, that._eventDown);

            // ���ڵ����¼�
            _$window.bind('resize', that._winResize);
        },

        // ж���¼�����
        _removeEvent: function () {
            var that = this,
			DOM = that.DOM;

            DOM.wrap.unbind('click', that._click)
		.unbind(_eventDown, that._eventDown);
            _$window.unbind('resize', that._winResize);
        }

    };

    artDialog.fn._init.prototype = artDialog.fn;
    $.fn.dialog = $.fn.artDialog = function () {
        var config = arguments;
        this[this.live ? 'live' : 'bind']('click', function () {
            artDialog.apply(this, config);
            return false;
        });
        return this;
    };



    /** ���ĶԻ���API */
    artDialog.focus = null;



    /** �Ի����б� */
    artDialog.list = {};



    // ȫ�ֿ�ݼ�
    _$document.bind('keydown', function (event) {
        var target = event.target,
		nodeName = target.nodeName,
		rinput = /^INPUT|TEXTAREA$/,
		api = artDialog.focus,
		keyCode = event.keyCode;

        if (!api || !api.config.esc || rinput.test(nodeName)) return;

        keyCode === 27 && api._trigger(api.config.noText);
    });



    // ��ȡartDialog·��
    _path = window['_artDialog_path'] || (function (script, i, me) {
        for (i in script) {
            // ���ͨ���������ű����������ر��ļ����뱣֤�ļ�������"artDialog"�ַ�
            if (script[i].src && script[i].src.indexOf('artDialog') !== -1) me = script[i];
        };

        _thisScript = me || script[script.length - 1];
        me = _thisScript.src.replace(/\\/g, '/');
        return me.lastIndexOf('/') < 0 ? '.' : me.substring(0, me.lastIndexOf('/'));
    } (document.getElementsByTagName('script')));




    // ����������CSS (��"artDialog.js?skin=aero")
    _skin = _thisScript.src.split('skin=')[1];
    if (_skin) {
        var link = document.createElement('link');
        link.rel = 'stylesheet';
        link.href = _path + '/skins/' + _skin + '.css?' + artDialog.fn.version;      
        alert(link.href);
        _thisScript.parentNode.insertBefore(link, _thisScript);
    };



    // ���������Ԥ�Ȼ��汳��ͼƬ
    _$window.bind('load', function () {
        setTimeout(function () {
            if (_count) return;
            artDialog({ time: 9, left: '-9999em', fixed: false, lock: false, focus: false });
        }, 150);
    });



    // ����IE6 CSS����ͼƬ����
    try {
        document.execCommand('BackgroundImageCache', false, true);
    } catch (e) { };



    /** ģ�� */
    artDialog.templates = [
'<div class="aui_outer">',
	'<table class="aui_border">',
		'<tbody>',
			'<tr>',
				'<td class="aui_nw"></td>',
				'<td class="aui_n"></td>',
				'<td class="aui_ne"></td>',
			'</tr>',
			'<tr>',
				'<td class="aui_w"></td>',
				'<td class="aui_center">',
					'<table class="aui_inner">',
						'<tbody>',
							'<tr>',
								'<td colspan="2" class="aui_header">',
									'<div class="aui_titleBar">',
										'<div class="aui_title"></div>',
										'<a class="aui_close" href="javascript:/*artDialog*/;">',
											'\xd7',
										'</a>',
									'</div>',
								'</td>',
							'</tr>',
							'<tr>',
								'<td class="aui_icon">',
									'<div class="aui_iconBg"></div>',
								'</td>',
								'<td class="aui_main">',
									'<div class="aui_content"></div>',
								'</td>',
							'</tr>',
							'<tr>',
								'<td colspan="2" class="aui_footer">',
									'<div class="aui_buttons"></div>',
								'</td>',
							'</tr>',
						'</tbody>',
					'</table>',
				'</td>',
				'<td class="aui_e"></td>',
			'</tr>',
			'<tr>',
				'<td class="aui_sw"></td>',
				'<td class="aui_s"></td>',
				'<td class="aui_se"></td>',
			'</tr>',
		'</tbody>',
	'</table>',
'</div>'
].join('');



    /**
    * Ĭ������
    */
    artDialog.defaults = {
        // ��Ϣ����
        content: '<div class="aui_loading"><span>loading..</span></div>',
        title: '\u6d88\u606f', 	// ����. Ĭ��'��Ϣ'
        button: null, 			// �Զ��尴ť
        yesFn: null, 			// ȷ����ť�ص�����
        noFn: null, 				// ȡ����ť�ص�����
        yesText: '\u786E\u5B9A', // ȷ����ť�ı�. Ĭ��'ȷ��'
        noText: '\u53D6\u6D88', 	// ȡ����ť�ı�. Ĭ��'ȡ��'
        width: 'auto', 			// ���ݿ��
        height: 'auto', 			// ���ݸ߶�
        minWidth: 96, 			// ��С�������
        minHeight: 32, 			// ��С�߶�����
        padding: '3px 15px', 	// ������߽�������
        skin: '', 				// Ƥ����(��Ƥ������Ԥ���ӿ�)
        icon: null, 				// ��Ϣͼ������
        initFn: null, 			// �Ի����ʼ����ִ�еĺ���
        closeFn: null, 			// �Ի���ر�ִ�еĺ���
        time: null, 				// �Զ��ر�ʱ��
        esc: true, 				// �Ƿ�֧��Esc���ر�
        focus: true, 			// �Ƿ�֧�ֶԻ���ť�۽�
        show: true, 				// ��ʼ�����Ƿ���ʾ�Ի���
        follow: null, 			// ����ĳԪ��
        path: _path, 			// artDialog·��
        lock: false, 			// �Ƿ�����
        background: '#000', 		// ������ɫ
        opacity: .7, 			// ����͸����
        duration: 300, 			// ����͸���Ƚ��䶯���ٶ�
        fixed: false, 			// �Ƿ�ֹ��λ
        left: '50%', 			// X������
        top: 'goldenRatio', 		// Y������
        zIndex: 1987, 			// �Ի�����Ӹ߶�ֵ(��Ҫ����ֵ���ܳ���������������)
        resize: true, 			// �Ƿ������û����ڳߴ�
        drag: true					// �Ƿ������û��϶�λ��

    };

    window.artDialog = $.dialog = $.artDialog = artDialog;
} ((window.jQuery && (window.art = jQuery)) || window.art, this));






/*!
��ѡ����ģ�飺������ק֧��
------------------------------------------------------------------*/
; (function ($) {

    var _dragEvent, _use,
	_$window = $(window),
	_$document = $(document),
	_elem = document.documentElement,
	_isIE6 = ! -[1, ] && !('minWidth' in _elem.style),
	_isLosecapture = 'onlosecapture' in _elem,
	_isSetCapture = 'setCapture' in _elem;

    // ��ק�¼�
    artDialog.dragEvent = function () {
        var that = this,
		proxy = function (name) {
		    var fn = that[name];
		    that[name] = function () {
		        return fn.apply(that, arguments);
		    };
		};

        proxy('start');
        proxy('move');
        proxy('end');
    };

    artDialog.dragEvent.prototype = {

        // ��ʼ��ק
        onstart: $.noop,
        start: function (event) {
            _$document
		.bind('mousemove', this.move)
		.bind('mouseup', this.end);

            this._sClientX = event.clientX;
            this._sClientY = event.clientY;
            this.onstart(event.clientX, event.clientY);

            return false;
        },

        // ������ק
        onmove: $.noop,
        move: function (event) {
            this._mClientX = event.clientX;
            this._mClientY = event.clientY;
            this.onmove(
			event.clientX - this._sClientX,
			event.clientY - this._sClientY
		);

            return false;
        },

        // ������ק
        onend: $.noop,
        end: function (event) {
            _$document
		.unbind('mousemove', this.move)
		.unbind('mouseup', this.end);

            this.onend(event.clientX, event.clientY);
            return false;
        }

    };

    _use = function (event) {
        var limit, startWidth, startHeight, startLeft, startTop, isResize,
		api = artDialog.focus,
		config = api.config,
		DOM = api.DOM,
		wrap = DOM.wrap,
		title = DOM.title,
		main = DOM.main;

        // ����ı�ѡ��
        var clsSelect = 'getSelection' in window ? function () {
            window.getSelection().removeAllRanges();
        } : function () {
            try {
                document.selection.empty();
            } catch (e) { };
        };

        // �Ի���׼���϶�
        _dragEvent.onstart = function (x, y) {
            if (isResize) {
                startWidth = main[0].offsetWidth;
                startHeight = main[0].offsetHeight;
            } else {
                startLeft = wrap[0].offsetLeft;
                startTop = wrap[0].offsetTop;
            };

            _$document.bind('dblclick', _dragEvent.end);
            !_isIE6 && _isLosecapture ?
			title.bind('losecapture', _dragEvent.end) :
			_$window.bind('blur', _dragEvent.end);
            _isSetCapture && title[0].setCapture();

            wrap.addClass('aui_state_drag');
            api.focus();
        };

        // �Ի����϶�������
        _dragEvent.onmove = function (x, y) {
            if (isResize) {
                var wrapStyle = wrap[0].style,
				style = main[0].style,
				width = x + startWidth,
				height = y + startHeight;

                wrapStyle.width = 'auto';
                style.width = Math.max(0, width) + 'px';
                config.width = wrap[0].offsetWidth;
                wrapStyle.width = config.width + 'px';
                config.height = Math.max(0, height);
                style.height = config.height + 'px';

            } else {
                var style = wrap[0].style,
				left = x + startLeft,
				top = y + startTop;

                style.left = Math.max(limit.minX, Math.min(limit.maxX, left)) + 'px';
                style.top = Math.max(limit.minY, Math.min(limit.maxY, top)) + 'px';
            };

            clsSelect();
            _isIE6 && api._selectFix();
        };

        // �Ի����϶�����
        _dragEvent.onend = function (x, y) {
            _$document.unbind('dblclick', _dragEvent.end);
            !_isIE6 && _isLosecapture ?
			title.unbind('losecapture', _dragEvent.end) :
			_$window.unbind('blur', _dragEvent.end);
            _isSetCapture && title[0].releaseCapture();

            _isIE6 && api._autoPositionType();

            wrap.removeClass('aui_state_drag');
        };

        isResize = event.target === DOM.se[0] ? true : false;
        limit = (function () {
            var maxX, maxY,
			wrap = api.DOM.wrap[0],
			fixed = wrap.style.position === 'fixed',
			ow = wrap.offsetWidth,
			oh = wrap.offsetHeight,
			ww = _$window.width(),
			wh = _$window.height(),
			dl = fixed ? 0 : _$document.scrollLeft(),
			dt = fixed ? 0 : _$document.scrollTop(),

            // �������ֵ����
		maxX = ww - ow + dl;
            maxY = wh - oh + dt;

            return {
                minX: dl,
                minY: dt,
                maxX: maxX,
                maxY: maxY
            };
        })();

        _dragEvent.start(event);
    };

    // ���� mousedown �¼������Ի����϶�
    _$document.bind('mousedown', function (event) {
        var api = artDialog.focus;
        if (!api) return;

        var target = event.target,
		config = api.config,
		DOM = api.DOM;

        if (config.drag !== false && target === DOM.title[0]
	|| config.resize !== false && target === DOM.se[0]) {
            _dragEvent = _dragEvent || new artDialog.dragEvent();
            _use(event);
            return false; // ��ֹfirefox��chrome����
        };
    });

})(window.jQuery || window.art);



import 'dart:io';
import 'dart:math';
import 'dart:ui' as ui;

import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';
import 'package:flutter_twitter_clone/helper/utility.dart';
import 'package:flutter_twitter_clone/model/user.dart';
import 'package:flutter_twitter_clone/ui/page/profile/widgets/circular_image.dart';
import 'package:path_provider/path_provider.dart';

import 'dot_indicator.dart';

class ScanScreen extends StatefulWidget {
  final UserModel user;

  const ScanScreen({Key? key, required this.user}) : super(key: key);

  static MaterialPageRoute getRoute(UserModel user) {
    return MaterialPageRoute(
        builder: (BuildContext context) => ScanScreen(user: user));
  }

  @override
  _ScanState createState() => _ScanState();
}

class _ScanState extends State<ScanScreen> with SingleTickerProviderStateMixin {
  late PageController pageController;
  double pageIndex = 0;
  GlobalKey globalKey = GlobalKey();

  @override
  void initState() {
    super.initState();
    pageController = PageController()..addListener(pageListener);
  }

  void pageListener() {
    setState(() {
      pageIndex = pageController.page!;
    });
  }

  Future<void> _capturePng() async {
    try {
      RenderRepaintBoundary boundary = globalKey.currentContext!
          .findRenderObject() as RenderRepaintBoundary;
      ui.Image image = await boundary.toImage(pixelRatio: 3.0);
      ByteData? byteData =
      await image.toByteData(format: ui.ImageByteFormat.png);
      var path = await _localPath + "/${DateTime.now().toIso8601String()}.png";
      await writeToFile(byteData!, path);

      Utility.shareFile([path], text: "");
    } catch (e) {
      print(e);
    }
  }

  Future<String> get _localPath async {
    final directory = await getApplicationDocumentsDirectory();
    return directory.path;
  }

  Future<File> writeToFile(ByteData data, String path) {
    final buffer = data.buffer;
    return File(path).writeAsBytes(
        buffer.asUint8List(data.offsetInBytes, data.lengthInBytes));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Stack(
          children: <Widget>[
            PageView.builder(
              controller: pageController,
              itemCount: 1,
              itemBuilder: (BuildContext context, int index) {
                return QrCode(user: widget.user, globalKey: globalKey);
              },
            ),
            Align(
              alignment: Alignment.topLeft,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  BackButton(color: Theme.of(context).colorScheme.onPrimary),
                  IconButton(
                    onPressed: _capturePng,
                    icon: Icon(
                      Icons.share_outlined,
                      color: Theme.of(context).colorScheme.onPrimary,
                    ),
                  ),
                ],
              ),
            ),
            _controls(),
          ],
        ),
      ),
    );
  }

  Widget _controls() {
    return Align(
      alignment: Alignment.bottomCenter,
      child: SizedBox(
        height: 50,
        child: DotsIndicator(
          controller: pageController,
          color: Theme.of(context).colorScheme.onPrimary,
          itemCount: 1,
          onPageSelected: (int page) {
            pageController.animateToPage(
              page,
              duration: const Duration(milliseconds: 750),
              curve: Curves.ease,
            );
          },
        ),
      ),
    );
  }

  @override
  void dispose() {
    pageController.dispose();
    super.dispose();
  }
}

class QrCode extends StatefulWidget {
  const QrCode({Key? key, required this.user, required this.globalKey})
      : super(key: key);

  final UserModel user;
  final GlobalKey globalKey;

  @override
  _QrCodeState createState() => _QrCodeState();
}

class _QrCodeState extends State<QrCode> {
  Color color = const Color(0xff07B7A6);

  Color get randomColor {
    final colors = <Color>[
      const Color(0xffFF7878),
      const Color(0xffFFA959),
      const Color(0xff83DA2D),
      const Color(0xff1FE2D7),
      const Color(0xffC13E6B),
      const Color(0xffFF7878),
      const Color(0xff07B7A6),
      const Color(0xff1F7ACD),
      const Color(0xffBB78FF),
      const Color(0xffF14CD7),
      const Color(0xffFF5757),
      const Color(0xff28B446),
    ];

    Random ran = Random.secure();
    return colors[ran.nextInt(colors.length)];
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Theme.of(context).dividerColor.withOpacity(.2),
      alignment: Alignment.center,
      child: InkWell(
        onTap: () {
          color = randomColor;
          setState(() {});
        },
        child: RepaintBoundary(
          key: widget.globalKey,
          child: Stack(
            alignment: Alignment.center,
            children: [
              ClipRRect(
                borderRadius: BorderRadius.circular(6),
                child: Container(
                  decoration: BoxDecoration(
                    color: color,
                    border: Border.all(
                      color: Theme.of(context).colorScheme.onPrimary,
                      width: 4,
                    ),
                    borderRadius: BorderRadius.circular(10),
                  ),
                  padding: const EdgeInsets.all(22),
                  width: MediaQuery.of(context).size.width * 0.7,
                  height: MediaQuery.of(context).size.width * 0.7,
                  child: Center(
                    child: Text(
                      "QR Code Placeholder",
                      style: TextStyle(
                        color: Theme.of(context).colorScheme.onPrimary,
                        fontSize: 16,
                      ),
                      textAlign: TextAlign.center,
                    ),
                  ),
                ),
              ),
              CircularImage(path: widget.user.profilePic, height: 50),
            ],
          ),
        ),
      ),
    );
  }
}

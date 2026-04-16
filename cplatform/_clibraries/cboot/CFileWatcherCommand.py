from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
import time

WATCH_PATH = "C:/Users/kevle/Desktop/cezdev/clibs/_clibs/c3dclassessdk"   # change this

class Handler(FileSystemEventHandler):
    def on_any_event(self, event):
        print(f"{event.event_type}: {event.src_path}")

observer = Observer()
observer.schedule(Handler(), path=WATCH_PATH, recursive=True)
observer.start()

try:
    while True:
        time.sleep(1)
except KeyboardInterrupt:
    observer.stop()

observer.join()

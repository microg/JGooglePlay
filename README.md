JGoogle
=====

An inofficial Google PlayStore Client library. Part of μg Project.

Used Libs
---------
This project makes use of the following external projects:
* [Google's micro-protobuf Library](http://code.google.com/p/micro-protobuf/)
  
	> License: [New BSD License](http://opensource.org/licenses/BSD-3-Clause)
	
	> Source: http://micro-protobuf.googlecode.com/svn/trunk/

Building
--------
1. Download and build micro-protobuf
2. Build protobuf-files in proto-folder

		mkdir -p gen && protoc --javamicro_out=gen proto/*.proto

3. Download and build [JGoogle](https://github.com/microg/JGoogle)
4. Compile src and gen folder (remember adding micro-protobuf and JGoogle to classpath)

License
-------
> Copyright 2012-2013 μg Project Team

> Licensed under the Apache License, Version 2.0 (the "License");
> you may not use this file except in compliance with the License.
> You may obtain a copy of the License at

> http://www.apache.org/licenses/LICENSE-2.0

> Unless required by applicable law or agreed to in writing, software 
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
> See the License for the specific language governing permissions and
> limitations under the License.
